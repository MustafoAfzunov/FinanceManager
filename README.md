**API Documentation**


https://finance-manager-lac-delta.vercel.app - DEPLOYED LINK OF THE PROJECT


This documentation provides an overview of the available API endpoints for user authentication, balance management, expenses, and incomes. The API supports operations for user registration, login, managing balances, and handling expenses and incomes.

**Base URL:**
http://localhost:8080

**Authentication**

**Register User**

Endpoint: POST http://localhost:8080/user/register

Description: Registers a new user.

**Request Body:**

**{
  "username": "string",  
  "email": "string", 
  "password": "string"   }**

**Response:**

    201 Created: User successfully registered
    "access Token": "<token>"
    ![image](https://github.com/user-attachments/assets/3318381f-86ee-4199-8766-79b14c822209)





**Login User:**

Endpoint: POST http://localhost:8080/user/login

Description: Logs in an existing user.

**Request Body:**

**{
  "username": "string", 
  "password": "string"  }**
  ![image](https://github.com/user-attachments/assets/26eae07b-a4e7-440f-8c52-ef7eba56bc6c)

  
If you use postman then you need to add the access token to the header section as an authorization claimer to successfully log in.

**Response:**

    200 OK: Successful login with a token or user info.
    ![image](https://github.com/user-attachments/assets/d1897f8c-726f-4b7a-9a9d-e61cd72451c1)

    401 Unauthorized: Invalid credentials.
    

**Balance Management**

**Get Balance**

Endpoint: GET http://localhost:8080/balance/get-balance

Description: Retrieves the current balance of the user.

**Response:**

**{
  "balance": 0.0  
}**

Response Codes:

    200 OK: Successfully fetched the balance.
    401 Unauthorized: User not authenticated.

**Expenses Management**

**Add Expense**

Endpoint: POST http://localhost:8080/expenses/add-expense

Description: Adds a new expense entry.

**Request Body:**

**{
  "category": "string",    
  "amount": 0.0,           
  "date": "string"       
  }**

Response:

    201 Created: Expense successfully added.
    400 Bad Request: Invalid or missing input.

**List Expenses**

Endpoint: GET http://localhost:8080/expenses/list-expenses

Description: Retrieves a list of all expenses.

**Response:**

**[
  {
    "category": "string",
    "amount": 0.0,
    "date": "string"
  },
  // More expense entries
]**

Response Codes:

    200 OK: Successfully fetched the expenses list.
    401 Unauthorized: User not authenticated.


**Incomes Management**

**Add Income**

Endpoint: POST http://localhost:8080/incomes/add-income

Description: Adds a new income entry.

**Request Body:**

**{
  "source": "string",  
  "amount": 0.0,       
  "date": "string"     
}**

**Response:**

    201 Created: Income successfully added.
    400 Bad Request: Invalid or missing input.

**List Incomes**

Endpoint: GET http://localhost:8080/incomes/list-incomes

Description: Retrieves a list of all incomes.

**Response:**

**[
  {
    "source": "string",
    "amount": 0.0,
    "date": "string"
  },
  // More income entries
]**

**Response Codes:**

    200 OK: Successfully fetched the incomes list.
    401 Unauthorized: User not authenticated.

**Response Status Codes**

    200 OK: The request was successful, and the server responded with the requested data.
    201 Created: The resource was successfully created (usually in response to POST requests).
    400 Bad Request: The request was malformed or missing required fields.
    401 Unauthorized: The request requires authentication, or the authentication was invalid.
    404 Not Found: The requested resource could not be found.
    500 Internal Server Error: An unexpected error occurred on the server.

Error Handling

**All API endpoints respond with appropriate HTTP status codes, and the error responses are structured as follows:**

**{
  "error": "Error message describing the issue"
}**

**Notes:**

    All endpoints that require authentication (like getting balance, adding expenses or incomes) assume that the user is logged in and has a valid authentication token.
    You may need to include an Authorization header with a Bearer token in requests that require authentication.

