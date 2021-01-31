# resource-server

Пример простоого сервера предоставления ресурсов.
В данном сервере авторизация заточена конкретно под наш сервер авторизации Oauth2.0 по адресу https://t.trusted.plus

Обычная схема в/д с сервером ресурсов выглядит так:
![oauthScheme](./image/AuthCodeFlowSequenceDiagram-1.png)

Что бы получить доступ доступ в серверу ресурсов необходимо: 
1. Зарегистрировать ваше приложение в сервисе t.trusted.plus;
   Мои приложения -> Нажать плюс в правом нижнем углу -> Выбрать тип приложения -> Заполнить необходимы поля -> 
   Нажать создать -> Перейти в созданное приложение и сохранить куда нить clientId, а так же secret
2. Сохранить данные clientId and secret вашего приложения
   
3. Получить access_token отправив запрос на 
   https://t.trusted.plus/idp/sso/oauth/token?grant_type=password&username=salex&password=qwerty123 где  
   * **username** = логин для входа в ваш личный кабинет t.trusted.plus,  
   * **password** = пароль от личного кабинета t.trusted.plus,  
   * **Authorization**: Basic Auth : **username**=clientId, **password**=secret из пункта 2.
   Придет ответ в формате json подобного вида:
   ```json 
    {
   "access_token": "9f7b4180-e939-48da-9d89-5bc1171533db",
   "token_type": "bearer",
   "refresh_token": "50e3aaca-a3b5-4ca2-a620-81475fef4942",
   "expires_in": 17,
   "scope": "userprofile"
   }
   ```
4. Отправить запрос http://localhost:8096/user/profile  
   Authorization: **Bearer token** вставить токен, полученный ранее.
