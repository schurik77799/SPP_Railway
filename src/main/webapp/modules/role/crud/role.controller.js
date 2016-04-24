/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.controller('RoleController', function ($scope, $window, RoleService) {

    $scope.removeRow = function(id){
        var index = -1;
        var comArr = eval( $scope.roles );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i].id === id ) {
                index = i;
                break;
            }
        }
        if( index === -1 ) {
            alert( "Something gone wrong" );
        } else {
            const object = comArr[index];
            const action = {
                id : 2
            };

            RoleService.removeRow({ role: object, action: action });
            $scope.roles.splice(index, 1);
        }

    };
    $scope.updateRow = function(id){
        var index = -1;
        var comArr = eval( $scope.roles );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i].id === id ) {
                index = i;
                break;
            }
        }
        if( index === -1 ) {
            alert( "Cannot update row with id" + id);
        } else {
            const object = comArr[index];
            const action = {
                id : 1
            };

            RoleService.updateRow({ role: object, action: action });
        }
    };
    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const object = {
            id: $scope.roleId,
            name: $scope.roleName
        };

        const action = {
            id : 0
        };

        $scope.asyncRequestComplited = false;

        var smth = RoleService.register({role:object, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                $scope.events.push.apply($scope.events, data.eventList);

                $scope.asyncRequestComplited = true;
            });
        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
                // $window.location.href = '/';
                $scope.roleId = "";
                $scope.roleName = "";

                RoleService.getRoles()
                    .then(function(data) {
                        $scope.roles = data.data.roles;
                    });
            }
        });

        return smth;
    }

    return RoleService.getRoles()
        .then(function(data) {
            $scope.roles = data.data.roles;
        });

});