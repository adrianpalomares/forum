<h1 align="center">Welcome to forum-api 👋</h1>
<p>
</p>

> An API for storing forum posts. Built with Spring Boot.

## Installation

Before starting this application, the following environment variables need to be set.

- `SPRING_DATASOURCE_URL`
    - This is the url of the database. eg. `jdbc:mysql://localhost:3306/forum`. `forum` is the table name.
- `SPRING_DATASOURCE_USERNAME`
    - This is the username for the database. eg. `root`
- `SPRING_DATASOURCE_PASSWORD`
    - This is the password for the database user
- `SPRING_SECRET_KEY`
    - This is the secret key that is used to sign the [jwts](https://jwt.io/introduction)
- `SPRING_MAIL_USERNAME`
    - Username for email account. At the moment, the application is configured to use gmail. It can be changed in appliation.properties
- `SPRING_MAIL_PASSWORD`
    - Password for the email account


If you are using an IDE, such as Eclipse or Intellij, you may have to set the environment variables within the IDE
itself. Steps for
Intellij [here](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html#add-environment-variables)

## API Documentation

### Likes

**GET** `/api/posts/{id}/likes`

| METHOD      | ROUTE       | DESCRIPTION |
| ----------- | ----------- | ----------- |
| GET      | `/api/posts/{id}/likes`      | Retreive likes from a specific post. `{id}` is the post's id.
| POST   | `/api/posts/{id}/likes`       |  Where like submission occurs.

#### Like Format

```javascript
{
  "userId": 7, // The user's id.
  "value": true   // The value of the like. true for like, false for dislike
}
```

## Author

👤 **Adrian Palomares**

* Github: [@adrianpalomares](https://github.com/adrianpalomares)

## Show your support

Give a ⭐️ if this project helped you!

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_
