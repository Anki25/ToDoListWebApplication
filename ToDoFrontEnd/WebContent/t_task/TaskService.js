/**
 * 
 */
'use strict';

app.factory('TaskService',['$http','$q','$rootScope',function($http,$q,$rootScope){
	
	console.log("TaskService..")
	
	var BASE_URL='http://localhost:8077/ToDoBackEnd'
		return{
		
		/* GET LIST OF ALL TASKS */
		
fetchAllTasks:function(){
			return $http.get(BASE_URL+'/tasksByUser/')
			.then(
					function(response){
						console.log('getting list of tasks taskService');
						return response.data;
					},
					function(errResponse){
						console.error('Error while fetching TaskDetails');
						return $q.reject(errResponse);
					}				
	);
},

/*CREATE A TASK*/ 

createTask:function(task){
	console.log('caling create task in taskService')
	return $http.post(BASE_URL+'/tasks/',task)
	.then(
			function(response){
				return response.data;
			},
			function(errResponse){
				console.error('Error while creating Task');
				return $q.reject(errResponse);
			}				
);
},

/* UPDATE A TASK*/ 

updateTask:function(id,task){
	console.log('updating task TaskService');
	return $http.put(BASE_URL+'/tasksUpdate/'+id,task)
	.then(
			function(response){
				return response.data;
			},
			function(errResponse){
				console.error('Error while updating Task');
				return $q.reject(errResponse);
			}				
);
},

 /*DELETE A TASK*/ 

deleteTask:function(id){
	console.log('deleting task TaskService');
	return $http.delete(BASE_URL+'/tasksDelete/'+id)
	.then(
			function(response){
				return response.data;
			},
			function(errResponse){
				console.error('Error while deleting Task');
				return $q.reject(errResponse);
			}				
);
}, 

 /*GET SELECTED TASK DETAILS*/ 

getTask: function(id){
	return $http.get(BASE_URL+'/tasks/'+id)
	.then(                                        //success handler,failure handler
			function(response){
				$rootScope.selectedTask=response.data
				return response.data;
			},
			function(errResponse){
				console.error('Error while getting Task');
				return $q.reject(errResponse);
			}				
);
},
};  //return	
}
]
);
