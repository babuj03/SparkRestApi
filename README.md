# SparkRestApi
 Money Transfer Rest Api

 
Method 	  	 :   Post
URL  		 	 :   http://localhost:4567/create/accounts 
Request Body  	 :  {"currentBalace":100.0,"branch":"London","accountHolderName":"Jone", "accountHolderDOB":{"year":1989,"month":01,"day":10}, "active":true }



Method 	  	  :   GET
URL  			  :   http://localhost:4567/account/summary/1

 

Method 	  	  :   PUT
URL  			  :   http://localhost:4567/money/transfer 
Request Body  	  :  {"fromAccountNumber":1, "toAccountNumber":2, "amount":100.0}