package ferreirafelipe.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException {
  public UserFoundException() {
    super("Usuário ja cadastrado na base de dados");
  }
}
