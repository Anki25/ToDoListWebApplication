/**
 * 
 */
'use strict';

app.controller('TaskController', [
		'$scope',
		'TaskService','UserService',
		'$location',
		'$rootScope',
		function($scope, TaskService, $location, $rootScope) {
			console.log("TaskController...")
			var self = this; // self is alias name instead directly use this
			self.task = { // initialization
				task_id : '',
				user_id:'',
				title : '',
				task_date : '',
				description:'',
				status : '',
				ErrorCode:'',
				ErrorMessage:''
			};
			self.tasks = [];
						
				
			/*GET SELECTED TASK DETAILS*/

			self.getSelectedTask = getTask

			function getTask(task_id) {
				console.log("getting task! " + task_id)
			  TaskService.getTask(task_id).then(function(d) {
					self.task = d;					
				}, function(errResponse) {
					console.error('Error while fetching task details');
				});
			};

			/* GET LIST OF ALL TASKS */

			self.fetchAllTasks = function() {
				console.log("getting list of tasks");
				TaskService.fetchAllTasks()
				.then(function(d) { 
					self.tasks = d;
				}, function(errResponse) {
					console.error('Error while fetching Tasks');
				});
			};
			self.fetchAllTasks();

			/*CREATE A TASK*/ 

			self.createTask = function(task) {
				console.log('submit a new task',self.task);
				TaskService.createTask(task)
				.then(function(d){
				   self.task=d;	
				},
				function(errResponse) {
							console.error('Error while creating Tasks');
						});
			};

			/* UPDATE A TASK*/ 

			self.updateTask = function(task_id,task) {
				console.log('updating task TaskController');
				TaskService.updateTask(task_id,task).then(self.fetchAllTasks,
						function(errResponse) {
							console.error('Error while updating Tasks');
						});
			};	

			/* DELETE A TASK*/ 
			
			self.deleteTask = function(task_id) {
				console.log('deleting task TaskController');
				TaskService.deleteTask(task_id).then(self.fetchAllTasks,
						function(errResponse) {
							console.error('Error while deleting Tasks');
						});
			};	
			

self.submit = function() {
		console.log('Saving New Task', self.task);
		
		self.createTask(self.task);
		self.reset();
};

self.edit=function(){
	
	console.log('Updating Task', self.task);
	
	self.updateTask(self.task.task_id,self.task);
	

self.reset();
};

self.reset=function(){
	console.log('resetting the task',self.task);
	self.task={
			task_id : '',
			user_id:'',
			title : '',
			task_date : '',
			description:'',
			status : '',
			ErrorCode:'',
			ErrorMessage:''
		};
     $scope.myForm.$setPristine(); //reset Form


			 //END OF ALL 
};
	
		} ]);