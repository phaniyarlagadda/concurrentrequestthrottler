concurrentrequestthrottler
==========================
Implentation of concurrent request throttler for a REST API using Spring. When an expensive API is opened for public consumption we might want to limit the usage based on user/ip to prevenet abuse of the system. This is implemented by using concept of interecptors in Spring and a custom data stracture which uses Semaphores. For detailed description of implementation see below link

http://rationalcoding.blogspot.com/2014/03/concurrent-requests-limiter.html
