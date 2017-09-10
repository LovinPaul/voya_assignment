# voya_assignment

API Calls

1)UserOpCRUD
  a) /newUser
  Param : email, name. Create new user. 
  b) /getUser
  Param : email. Get user. 

2)AccountOpCRUD
  a) /newAccount
  Param : email. Create a new account for user.
  b) /getAccount
  Param : email, index. Get account of a user. 

3)AccountAudit
  a) /withdraw
  Param : email, index, amount. Withdraw from an account. 
  b) /deposit
  Param : email, index, amount. Deposit to an account. 
  c) /transfer
  Param : email, fromIndex, toIndex, amount. Transfer from an account to other account. 
  d) /balance
  Param : email, index. Get account balance. 