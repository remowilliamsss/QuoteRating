# QuoteRating
 Technical task for Kameleoon

 App's address - **`31.184.254.8`**
 
### Quotes

* **POST** _`/api/quotes`_ - adds a quote

request body:

`{
    "content": "Programming isn't about what you know; it's about what you can figure out.",
    "user": {
        "name" : "Chris Pine"
    }
}`

* **GET** _`/api/quotes/{id}`_ - finds a quote by id


* **GET** _`/api/quotes/random`_ - shows a random quote


* **PATCH** _`/api/quotes/{id}`_ - modifies a quote

request body:
`{
    "content": "Programming isn't about what you know. It's about what you can figure out.",
    "user": {
        "name" : "Chris Pine"
    }
}`

* **DELETE** _`/api/quotes/{id}`_ - deletes a quote by id


* **PATCH** _`/api/quotes/{id}/upvote`_ - upvotes a quote

request body:
`{
    "name" : "Chris Pine"
}`

* **PATCH** _`/api/quotes/{id}/downvote`_ - downvotes a quote

request body:
`{
    "name" : "Chris Pine"
}`

* **GET** _`/api/quotes/top10`_ - shows the top 10 quotes


* **GET** _`/api/quotes/flop10`_ - shows the worse 10 quotes


example of response:

`{
    "content": "Programming isn't about what you know. It's about what you can figure out.",
    "dateOfCreation": "2023-02-13T10:54:19.345177",
    "dateOfUpdate": "2023-02-13T11:03:20.396116",
    "user": {
        "name": "Chris Pine",
        "dateOfCreation": "2023-02-13T10:52:16.870059"
    },
    "score": 1,
    "votes": [
        {
            "user": {
            "name": "Grace Hopper",
            "dateOfCreation": "2023-02-13T11:28:10.960304"
            },
            "isPositive": true,
            "dateOfVote": "2023-02-13T11:31:13.163623"
        }
            ],
    "voteStatistics": {
        "2023-02-13T11:31:13.163623": 1
    }
}`

### Users

* **POST** _`/api/users`_ - creates a user

request body:

`{
    "name" : "Grace Hopper",
    "email": "grace@gmail.com",
    "password": 123
}`