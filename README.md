# StockMarketApi
Stock Market Api

Stock Market API using Spring Boot:

The Stock Market API has list of below api:

GET /api/stocks (get a list of stocks) 
POST /api/stocks (create a stock) 
GET /api/stocks/1 (get one stock from the list) 
PATCH /api/stocks/1 (update the price of a single stock) 
DELETE/api/stocks/1 (delete a single stock)

1.	POST /api/stocks (create a stock) 


 

2.	http://localhost:8080/GET/api/stocks

 

{
    "totalItems": 4,
    "Stocks": [
        {
            "id": 1,
            "name": "Apple",
            "currentprice": 10.5600,
            "lastupdate": "2022-02-13T18:36:03.082+00:00"
        },
        {
            "id": 2,
            "name": "Google",
            "currentprice": 100.5600,
            "lastupdate": "2022-02-13T18:36:03.103+00:00"
        },
        {
            "id": 3,
            "name": "Oracle",
            "currentprice": 1000.5600,
            "lastupdate": "2022-02-13T18:36:03.104+00:00"
        }
    ],
    "totalPages": 2,
    "currentPage": 0
}
3.	http://localhost:8080/GET/api/stocks/3

 

4.	http://localhost:8080/PATCH/api/stocks/3

 
 

5.	DELETE/api/stocks/1 (delete a single stock)

 

6.	Paging : http://localhost:8080/GET/api/stocks?page=0

 
7.	Paging and Size
 

{
    "totalItems": 3,
    "Stocks": [
        {
            "id": 2,
            "name": "Google",
            "currentprice": 100.5600,
            "lastupdate": "2022-02-13T18:36:03.103+00:00"
        }
    ],
    "totalPages": 3,
    "currentPage": 1
}


Generate the jar:

 

Or 

Run mvn clean package


Docker Commands:
Run
docker build --tag=stockmarketapp:latest .
 

 



 

 


