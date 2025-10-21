# Planned API routes for Budget App

## GET Routes

### User Entity

- Get all users 
- Get user by email
- get user by last name

### Transaction Entity

- Get all transactions
- Get transaction by email
- Get transaction by email and category
- Get transactions by email and category and date range


### Transaction Category Entity

- Get description by category name
- Get cashflowtype and cashflow factor by category name
- Get all categories by cashflow type name
- Get all categories

### Cashflow Type Entity

- Get all cashflow types
- Get cashflow description by cashflow name
- Get cashflow factor by cashflow name

### Budget Entity

- Get budget sum and count of transactions by email and category and budget period
- Get budget sum and count of transactions by email and category and budget period range
- Get budget sum and count of transactions by email
- Get budget sum and count of transactions by email and category 

## POST Routes



### User Entity

- Add user

### Transaction Entity

- Add transaction

### Transaction Category Entity

- Add transaction category (maybe not needed, not a user item)

### Cashflow Type Entity

- Add cashflow type (maybe not needed, not a user item)

### Budget Entity

- Add budget for budget period by user and category

## PUT Routes


### User Entity

- Update user 

### Transaction Entity

- Update Transaction

### Transaction Category Entity

- Update Transaction Category

### Cashflow Type Entity

- Update Cashflow Type

### Budget Entity

- Update Budget

## DELETE Routes

### User Entity

- Delete User

### Transaction Entity

- Delete Transaction entry

### Transaction Category Entity

- Delete Transaction Category ((maybe not needed, not a user item)

### Cashflow Type Entity

- Delete cashflow type (maybe not needed, not a user item)

### Budget Entity

- Delete budget entry
