# SparkRestApi
 ![Total visitor](https://visitor-count-badge.herokuapp.com/total.svg?repo_id=SparkRestApi)
  Money transfer Rest Api has three end points, which are implemented by using SPARK.
     1.	Bank Account Creation.
     2.	Fetch Account by Id.
     3.	Money Transfer between two Accounts.

 <h2>Start the application by running BankAccountResource</h2>

 
Method 	  	 :   Post
URL  		 	 :   http://localhost:4567/create/account
Request Body  	 :  {"currentBalace":100.0,"branch":"London","accountHolderName":"Jone", "accountHolderId":"ID123456","accountHolderDOB":{"year":1989,"month":01,"day":10}, "active":true }



Method 	  	  :   GET
URL  			  :   http://localhost:4567/account/summary/1

 

Method 	  	  :   PUT
URL  			  :   http://localhost:4567/money/transfer 
Request Body  	  :  {"fromAccountNumber":1, "toAccountNumber":2, "amount":100.0}
