/**
 * 
 */
'use strict';

app.factory('UserService',['$http','$q','$rootScope',function($http,$q,$rootScope){
	
	console.log("UserService..")
	
	var BASE_URL='http://localhost:8077/ToDoBackEnd'
		return{
		
		/*GET SELECTED USER DETAILS*/ 

		getUser: function(id){
			return $http.get(BASE_URL+'/userInfo/'+id)
			.then(                                        //success handler,failure handler
					function(response){
						$rootScope.selectedUser=response.data
						return response.data;
					},
					function(errResponse){
						console.error('Error while getting User');
						return $q.reject(errResponse);
					}				
		);
		},

	
fetchAllUsers:function(){
			return $http.get(BASE_URL+'/users')
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while fetching UserDetails');
						return $q.reject(errResponse);
					}				
	);
},
createUser:function(user){
	return $http.post(BASE_URL+'/userInfo/',user)
	.then(
			function(response){
				console.log('calling createUser in UserService');
				return response.data;
			},
			function(errResponse){
				console.error('Error while creating User');
				return $q.reject(errResponse);
			}				
);
	
},
updateUser:function(user,id){
	return $http.put(BASE_URL+'/userInfo/'+id,user)
	.then(
			function(response){
				return response.data;
			},
			function(errResponse){
				console.error('Error while updating User');
				return $q.reject(errResponse);
			}				
);
},
deleteUser:function(id){
	return $http.delete(BASE_URL+'/userInfo/'+id)
	.then(
			function(response){
				return response.data;
			},
			function(errResponse){
				console.error('Error while deleting User');
				return $q.reject(errResponse);
			}				
);
},
authenticate:function(user){
	return $http.post(BASE_URL+'/userInfo/authenticate/',user)
	.then(
			function(response){
				console.log('Authenticating UserService');
				if(response.data.errorMessage!="")
					{
					$rootScope.currentUser={
							name:response.data.user_name,
							id:response.data.user_id,
							role:response.data.role
				};
					}
				return response.data;
			}
			);
},
logout:function(user){
	console.log('Logged Out UserService');
	return $http.get(BASE_URL+'/userInfo/logout')
	.then(
			function(response){
				if(ErrorCode==200){
				return response.data;
				$location.path('/');}
			},
			function(errResponse){
				console.error('Error while logging out');
				return $q.reject(errResponse);
			}				
);
},

}
}
]
);
