# language: pt
# charset: UTF-8
Funcionalidade: Efetuar login na plataforma
  Eu como cliente gostaria de acessar o sistema
  E validar o login com as credenciais

  Contexto: Acessar a plataforma
    Dado que acesse a pagina de login
  @wip
  Cenario: Executar login com as credenciais validas
    Quando informar as credenciais "usuario_valido"
    Entao sera efetuado o login com sucesso