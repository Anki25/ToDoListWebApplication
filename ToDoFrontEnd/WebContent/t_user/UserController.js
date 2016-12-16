/**
 * 
 */
'use strict';

app.controller('UserController', [
		'$scope',
		'UserService',
		'$location',
		'$rootScope',
		function($scope, UserService, $location, $rootScope) {
			console.log("UserController...")
			var self = this;
			self.user = {
				user_id : '',
				user_name : '',
				password : '',
				cpassword:'',
				contact : '',
				email : '',
				profession:'',
				city:'',
				ErrorCode : '',
				ErrorMessage:''
			};
			self.users = [];
			
			/*GET SELECTED USER DETAILS*/

			self.getSelectedUser = getUser

			function getUser(user_id) {
				console.log("getting user! " + user_id)
			  UserService.getUser(user_id).then(function(d) {
					self.user = d;					
				}, function(errResponse) {
					console.error('Error while fetching user details');
				});
			};


			self.fetchAllUsers = function() {
				UserService.fetchAllUsers().then(function(d) {
					self.users = d;
				}, function(errResponse) {
					console.error('Error while fetching Users');
				});
			};
			self.fetchAllUsers();

			/* CREATE USER */
			self.createUser = function(user) {
				console.log('submit a new user',self.user);
				UserService.createUser(user)
				.then(function(d){
				   self.user=d;	
				},
				function(errResponse) {
							console.error('Error while creating User');
						});
			};

			/* UPDATE USER */
			self.updateUser = function(user, id) {
				UserService.updateUser(user, id)
				.then(self.fetchAllUsers,
						function(errResponse) {
							console.error('Error while updating User...');
						});
			};

			/* AUTHENTICATION OF USER */
			self.authenticate = function(user) {
				UserService.authenticate(user)
				.then(function(d) {
					console.log('authenticating user in UserController');
					self.user = d;
					if ($rootScope.currentUser) {
						$location.path('/');
					}
				}, function(errResponse) {
					console.error('Error while authenticate User...');
				});
			};

			/* DELETE USER */
			self.deleteUser = function(id) {
				UserService.deleteUser(id)
				.then(self.fetchAllUsers,
						function(errResponse) {
							console.error('Error while deleting User');
						});
			};

			self.login = function() {
				{
					console.log('login validation????', self.user);
					self.authenticate(self.user);
				}
			};
			
			
			
			self.remove = function(id) {
				console.log('id to be deleted', id);
				if (self.user.id == id) {
					self.reset();
				}
				self.deleteUser(id);
			};
			
			self.logout=function(user){
				console.log('Logging out');
				UserService.logout()
				.then(function(user) {
					if ($rootScope.currentUser='') {
						$location.path("/");
						alert("You have successfully logged out!")
					}},
						/*$rootScope.currentUser={}
						$cookieStore.remove('currentUser');*/
						
						/*$location.path("/")*/
						function(errResponse) {
					console.error('Error while logging out');
				});
				
				self.submit = function() {
						console.log('Saving New user', self.user);
						self.createUser(self.user);
					
				self.reset();
				};
				
				
				self.reset=function(){
					console.log('reset user',self.user);
					self.user={
							user_id : '',
							user_name : '',
							password : '',
							cpassword:'',
							contact : '',
							email : '',
							profession:'',
							city:'',
							ErrorMessage : '',
							ErrorCode:''

						};
				     $scope.myForm.$setPristine(); // reset Form
				};

	
			/* END OF ALL */
};
	
		} ]);
