## 1.1. Метод subscription (auth) 
**входящие данные**<br/>
```json
{
  "pubUserId": 10
}
```
**валидация**<br/>
pubUserId > 0

**логика метода**<br/>
сервис проверяет, что pubUserId не равен id юзера реквеста, в случае равенства возвращает ошибку SUBSCRIPTION_LOGIC_ERROR,<br/>
далее сервис по pubUserId ищет юзера в таблице user, если не находит - возвращает ошибку PUBLISHER_USER_NOT_FOUND,<br/>
если находит - проверят наличие такой подписки в таблице subscription, если находит - возвращает ошибку ALREADY_SUBSCRIBED,<br/>
если не находит - добавляет подписку.

**исходящие данные в случае успеха** <br/>статус 200

## 1.2. Метод unsubscription (auth)
**входящие данные**<br/>
```json
{
  "pubUserId": 5
}
```
**валидация**<br/>
pubUserId > 0

**логика метода**<br/>
сервис совершает попытку удалить подписку по пришедшим данным

**исходящие данные в случае успеха** <br/>статус 200

## 2.1. Метод getMyPublishers (auth)
**логика метода**<br/>
сервис возвращает подписки юзера

**исходящие данные в случае успеха** <br/>статус 200
```json
{
  "data": {
    "publishers": [
      {
        "userId": 1,
        "nickname": "barabashka"
      },
      {
        "userId": 2,
        "nickname": "barabashka1"
      },
      {
        "userId": 3,
        "nickname": "1111"
      },
      {
        "userId": 9,
        "nickname": "добкварли"
      }
    ]
  }
}
```
## 2.2. Метод getMySubscribers (auth)
**логика метода**<br/>
сервис возвращает подписчиков юзера

**исходящие данные в случае успеха** <br/>статус 200
```json
{
  "data": {
    "subscribers": [
      {
        "userId": 9,
        "nickname": "добкварли"
      },
      {
        "userId": 8,
        "nickname": "добликвар"
      }
    ]
  }
}
```

## 3. Метод getMyPublishersPosts (auth)

**входящие данные**<br/>
/getMyPublishersPosts/{from}/{limit}

**валидация**<br/>
from >= 0, limit > 0

**логика метода**<br/>
сервис возвращает посты подписчиков юзера, сортировка от новых к старым


**исходящие данные в случае успеха** <br/>статус 200
```json
{
  "data": {
    "posts": [
      {
        "postId": 32,
        "userId": 1,
        "nickname": "barabashka",
        "text": "А я кавалер",
        "timeInsert": "2022-11-21 10:42:15",
        "tags": [
          {
            "tagId": 20,
            "text": "кавалер"
          },
          {
            "tagId": 21,
            "text": "бал"
          }
        ],
        "comments": [
          {
            "userId": 9,
            "nickname": "арбузо",
            "commentId": 6,
            "text": "вот это великая фраза!",
            "timeInsert": "2022-12-13 14:52:22"
          }
        ]
      },
      {
        "postId": 20,
        "userId": 4,
        "nickname": "barabashka89",
        "text": "А сегодня непогода",
        "timeInsert": "2022-11-17 16:11:40",
        "tags": [
          {
            "tagId": 16,
            "text": "непогожий"
          },
          {
            "tagId": 18,
            "text": "непогода"
          }
        ]
      },
      {
        "postId": 17,
        "userId": 4,
        "nickname": "barabashka89",
        "text": "А сегодня непогода",
        "timeInsert": "2022-11-17 16:11:33",
        "tags": [
          {
            "tagId": 16,
            "text": "непогожий"
          },
          {
            "tagId": 17,
            "text": "слякоть"
          }
        ]
      }
    ]
  }
}
```