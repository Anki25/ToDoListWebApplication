var app=angular.module('myApp',['ngRoute']);

app.config(function($routeProvider){
	$routeProvider
	
	.when('/',{
		templateUrl:'t_home/home.html',
		controller:'HomeController'
	})
	
	.when('/home',{
		templateUrl:'t_home/home.html',
		controller:'HomeController'
	})

	.when('/about',{
		templateUrl:'t_home/about.html',
	})
	
	.when('/contact',{
		templateUrl:'t_home/contact.html',
	})

	.when('/login',{
		templateUrl:'t_user/login.html',
		controller:'UserController'
	})
	
	.when('/signup',{
		templateUrl:'t_user/signup.html',
		controller:'UserController'
		
	})
	/**
	 * TASKS RELATED MAPPING
	 **/
	
	.when('/create_task',{
		templateUrl:'t_task/create_task.html',
		controller:'TaskController'
	})
	
	.when('/list_task',{
		templateUrl:'t_task/list_task.html',
		controller:'TaskController'
	})
	
	.when('/view_task',{
		templateUrl:'t_task/view_task.html',
		controller:'TaskController'
	})
	
	.when('/update_task',{
		templateUrl:'t_task/update_task.html',
		controller:'TaskController'
	})
	
	.when('/view_pendingTask',{
		templateUrl:'t_task/view_pendingTask.html',
		controller:'TaskController'
	})
	
	.otherwise({redirectTo: '/'});

});
