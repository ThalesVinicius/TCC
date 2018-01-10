package br.edu.ifnmg.estagio.Infraestrutura;

import br.edu.ifnmg.estagio.entidades.Pessoa;
import br.edu.ifnmg.estagio.repositorios.PessoaRepositorio;
import java.io.Serializable;
import java.util.Random;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

@RequestScoped
public class AutenticacaoService implements Serializable {

    @Inject
    SessaoService sessao;

    Pessoa pessoa;
    @EJB
    PessoaRepositorio daoPessoa;

    public boolean login(String email, String senha) {

        try {

            pessoa = daoPessoa.Abrir(email);

        } catch (Exception e) {

            return false;
        }

        if (pessoa == null) {

            return false;
        } else {
            if (senha.equals(pessoa.getSenha())) {

                sessao.put("usuarioAutenticado", pessoa.getId().toString());

                return true;
            } else {

                return false;

            }
        }
    }

    public boolean logout() {

        sessao.delete("usuarioAutenticado");

        return true;
    }

    public Pessoa getUsuarioCorrente() {

        if (pessoa == null) {
            String id = sessao.get("usuarioAutenticado");

            if (id != null) {

                pessoa = daoPessoa.Abrir(Long.parseLong(id));

            }
        }
        return pessoa;
    }

    public boolean redefinirSenha(String email) {
        try {
            pessoa = daoPessoa.Abrir(email);
        } catch (Exception e) {

        }

        if (pessoa == null) {
            return false;
        } else {
            String msg = "Sua nova senha de acesso ao Sistema de Gerenciamento de Estágios é: ";
            String senhaantiga = pessoa.getSenha();
            String tmpsenha = gerarSenha();
            pessoa.setSenha((tmpsenha));
            msg = msg + (tmpsenha);

            if (Enviarmail(pessoa.getEmail(), "Redefinição de Senha", msg)) {
                daoPessoa.Salvar(pessoa);
                return true;
            } else {
                System.err.println("teste email");
                pessoa.setSenha(senhaantiga);
                return false;
            }

        }
    }

    private String gerarSenha() {
        String alfabeto = "abcdefghijklmnopqrstuvxz0123456789!@#$%&*()-+;.:ABCDEFGHIJKLMNOPQRSTUVXZ";
        Random rnd = new Random();
        StringBuilder tmp = new StringBuilder();
        while (tmp.length() < 8) {
            tmp.append(alfabeto.charAt(rnd.nextInt(alfabeto.length())));
        }

        return tmp.toString();
    }

    public boolean Enviarmail(String email, String assunto, String msg) {

        try {
            SimpleEmail mail = new SimpleEmail();

            mail.setHostName("smtp.googlemail.com");
            mail.setSmtpPort(465);
            mail.setAuthenticator(new DefaultAuthenticator("sge.ifnmg@gmail.com", "sge_estagio"));
            mail.setSSLOnConnect(true);
   
            mail.setFrom("sge.ifnmg@gmail.com");
            mail.setSubject(assunto);
            mail.setMsg(msg);
            mail.addTo(email);
            mail.send();
            return true;

        } catch (EmailException e) {

            System.out.println(e.getMessage());
            return false;

        }

    }

}
