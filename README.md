# LattessinioBack

O backend do sistema de um Sistema Online de Gerenciamento de Currículos.

## Estrutura

![Captura de tela de 2020-08-17 20-12-00](https://user-images.githubusercontent.com/38709777/90453125-1f35fe00-e0c6-11ea-9505-feaff1def6ae.png)

## Funcionalidades
Lista com todos os endpoints:
| MÉTODO                        | URL                                         | AÇÃO                       |
| :---------------------------- | :-----------------------------------------: | -----------------------------------: |
|  GET                          | /api/users                                  | retorna todos Usuários           |
|  GET                          | /api/users?userName={userName}              | retorna Usuários que contem `userName` no nome  |
|  GET                          | /api/users?activityName={activityName}      | retorna Usuários cujo alguma atividade contem `activityName` no nome  |
|  POST                         | /api/users                                  | cria um novo Usuário                |
|  GET                          | /api/users/{id}                             | retorna Usuário pelo `id`       |
|  PUT                          | /api/users/{id}                             | modifica Usuário pelo `id`        |
|  DELETE                       | /api/users/{id}                             | deleta Usuário pelo `id`          |
|  GET                          | /api/activities                             | retorna todos Atividades           |
|  POST                         | /api/activities                             | cria uma nova Atividade           |
|  GET                          | /api/activities/{id}                        | retorna Atividade pelo `id`        |
|  PUT                          | /api/activities/{id}                        | modifica Atividade pelo `id`       |
|  DELETE                       | /api/activities/{id}                        | deleta Atividade pelo `id`         |

