angular.module('belajar', ['ui', 'belajar.controller'])
    .config(['$routeProvider', function($routeProvider){
        $routeProvider
            .when('/', {templateUrl: 'pages/home.html'})
            .when('/401', {templateUrl: 'pages/404.html', controller: 'LoginRedirectorController'})
            .when('/system/config', {templateUrl: 'pages/system/config.html', controller: 'ApplicationConfigController'})
            .when('/system/sessions', {templateUrl: 'pages/system/sessions.html', controller: 'ApplicationSessionsController'})
            .when('/system/user', {templateUrl: 'pages/system/user.html', controller: 'UserController'})
            .when('/system/role', {templateUrl: 'pages/system/role.html', controller: 'RoleController'})
            .when('/system/permission', {templateUrl: 'pages/system/permission.html', controller: 'PermissionController'})
            .when('/system/menu', {templateUrl: 'pages/system/menu.html', controller: 'SystemMenuController'})
            .when('/list/school', {templateUrl: 'pages/list/school.html'})
            .when('/list/class', {templateUrl: 'pages/list/class.html'})
            .when('/notifications/sms', {templateUrl: 'pages/notifications/sms.html'})
	    .when('/notifications/email', {templateUrl: 'pages/notifications/email.html'})
            .when('/laporan/lapPeserta', {templateUrl: 'pages/laporan/lapPeserta.html'})
            .when('/laporan/lapSoal', {templateUrl: 'pages/laporan/lapSoal.html'})
            .when('/laporan/lapUjian', {templateUrl: 'pages/laporan/lapUjian.html'})
            .when('/laporan/lapTopik', {templateUrl: 'pages/laporan/lapTopik.html'})
            .when('/laporan/lapPertanyaan', {templateUrl: 'pages/laporan/lapPertanyaan.html'})
            .when('/laporan/lapPilihan', {templateUrl: 'pages/laporan/lapPilihan.html'})
            .when('/laporan/school', {templateUrl: 'pages/laporan/school.html'})
            .when('/master/peserta', {templateUrl: 'pages/master/peserta.html'})
            .when('/master/soal', {templateUrl: 'pages/master/soal.html', controller: 'SoalController'})
            .when('/master/ujian', {templateUrl: 'pages/master/ujian.html', controller: 'UjianController'})
            .when('/master/topik', {templateUrl: 'pages/master/topik.html', controller: 'TopikController'})
            .when('/master/pertanyaan', {templateUrl: 'pages/master/pertanyaan.html'})
            .when('/master/pilihan', {templateUrl: 'pages/master/pilihan.html'})
            .when('/about', {templateUrl: 'pages/about.html', controller: 'AboutController'})
            .otherwise({templateUrl: 'pages/404.html'});
    }])
    .config(['$httpProvider', function($httpProvider){
        var sessionTimeoutInterceptor = ['$rootScope', '$location', '$q', function($rootScope, $location, $q){
            function success(response){
                return response;
            }
            
            function error(response){
                if (response.status === 401) {
                    $location.path('/401');
                }
            }
            
            return function(promise) {
                return promise.then(success, error);
            }
        }];
        
        
        $httpProvider.responseInterceptors.push(sessionTimeoutInterceptor);
        $httpProvider.responseInterceptors.push('httpLoadingSpinner');
        var spinnerFunction = function (data, headersGetter) {
            $('#loading').show();
            return data;
        };
        $httpProvider.defaults.transformRequest.push(spinnerFunction);
    }])
    .factory('httpLoadingSpinner', function ($q, $window) {
        return function (promise) {
            return promise.then(function (response) {
                // do something on success
                $('#loading').hide();
                return response;

            }, function (response) {
                // do something on error
                $('#loading').hide();
                return $q.reject(response);
            });
        };
    })
;
