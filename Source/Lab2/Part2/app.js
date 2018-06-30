var app = angular.module('ngOughts', ['ng']);

(function() {
    var Board = (function(){
        var S, emp, no, no2, marker, Cell;

        S = 3;
        emp = ' ';
        no = 'O';
        no2 = 'X';
        marker = [no, no2];

        Cell = (function() {
            function Cell(marker){
                this.marker = marker;
                this.winning = false;
            }
            Cell.prototype.mark = function(marker) {
                this.marker = marker;
            }
            Cell.prototype.alreadyPlayed = function() {
                return this.marker != emp;
            }
            return Cell;
        })();

        function Board() {
            this.reset();
        }
    // This will be that the grid is cleared out when reset is pressed
        Board.prototype.reset = function(){
            this.currentPlayer = 0;
            this.grid = [];
            this.won = false;
            this.gameGoingOn = true;

            for(var i=0; i < S; i++) {
                var row = [];

                for(var j=0; j < S; j++) {
                    row.push(new Cell(emp));
                }

                this.grid.push(row);
            }

            return this.grid;
        };
    // this is how you check to see what will be placed and making sure that you are not overlapping another symbol
        Board.prototype._checkRow = function(rowIndex) {
            var numberOfno = 0;
            var numberOfno2 = 0;

            for(var columnIndex = 0; columnIndex < S; columnIndex++) {
                var cell = this.grid[rowIndex];
                cell = cell[columnIndex];
                var cellM = cell.marker;
                if(cellM == emp) {
                    return false;
                }
                if(cellM == no) {
                    numberOfno++;
                } else if(cellM == no2) {
                    numberOfno2++;
                }
            }

            if(numberOfno == S) {
                return no;
            } else if(numberOfno2 == S) {
                return no2;
            }
        };

        Board.prototype._checkColumn = function(columnIndex) {
            var numberOfno = 0;
            var numberOfno2 = 0;

            for(var rowIndex = 0; rowIndex < S; rowIndex++) {
                var cell = this.grid[rowIndex][columnIndex];
                var cellM = cell.marker;
                if(cellM == emp) {
                    return false;
                }
                if(cellM == no) {
                    numberOfno++;
                } else if(cellM == no2) {
                    numberOfno2++;
                }
            }

            if(numberOfno == S) {
                return no;
            } else if(numberOfno2 == S) {
                return no2;
            }
        }

        Board.prototype._checkDiagonal1 = function() {
            var numberOfno = 0;
            var numberOfno2 = 0;

            for(var i = 0; i<S; i++) {
                var cellM = this.grid[i][i].marker;
                if(cellM == emp) {
                    return false;
                }
                if(cellM == no) {
                    numberOfno++;
                } else if(cellM == no2) {
                    numberOfno2++;
                }
            }

            if(numberOfno == S) {
                return no;
            } else if(numberOfno2 == S) {
                return no2;
            }
        }

        Board.prototype._checkDiagonal2 = function() {
            var numberOfno = 0;
            var numberOfno2 = 0;

            for(var i = 0; i<S; i++) {
                var cellM = this.grid[i][S-i-1].marker;
                if(cellM == emp) {
                    return false;
                }
                if(cellM == no) {
                    numberOfno++;
                } else if(cellM == no2) {
                    numberOfno2++;
                }
            }

            if(numberOfno == S) {
                return no;
            } else if(numberOfno2 == S) {
                return no2;
            }
        }
// here we are seeing if the game there is a winner or not we do this for every possiblity
        Board.prototype.setWinner = function(marker) {
            this.won = true;
            this.winning_marker = marker;
            this.gameGoingOn = false;
        };

        Board.prototype.WinnerRow = function(rowIndex) {
            for(var i = 0; i<S; i++) {
                this.grid[rowIndex][i].winning = true;
            }
        }

        Board.prototype.WinnerColumn = function(columnIndex) {
            for(var i = 0; i<S; i++) {
                this.grid[i][columnIndex].winning = true;
            }
        }

        Board.prototype.WinnerAcross = function() {
            for(var i = 0; i<S; i++) {
                this.grid[i][i].winning = true;
            }
        }

        Board.prototype.WinnerAcross2 = function() {
            for(var i = 0; i<S; i++) {
                this.grid[i][S-i-1].winning = true;
            }
        }


// here we are looking at the previous step and then declaring winner
        Board.prototype.isWinner = function() {
            var rowIndex = 0;
            var columnIndex = 0;
            var diagonal1 = 0;
            var diagonal2 = 0;

            for(rowIndex=0; rowIndex < S; rowIndex++) {
                var val = this._checkRow(rowIndex);
                if(val) {
                    this.setWinner(val);
                    this.WinnerRow(rowIndex);
                }
            }

            for(columnIndex = 0; columnIndex < S; columnIndex++) {
                val = this._checkColumn(columnIndex);
                if(val) {
                    this.setWinner(val);
                    this.WinnerColumn(columnIndex);
                }
            }

            val = this._checkDiagonal1();
            if(val) {
                this.setWinner(val);
                this.WinnerAcross();
            }

            val = this._checkDiagonal2();
            if(val) {
                this.setWinner(val);
                this.WinnerAcross2();
            }
        };

        Board.prototype.playCell = function(cell) {
            if(!(this.gameGoingOn)) {
                return;
            }
            if(cell.alreadyPlayed()) {
                return;
            }
            cell.mark(this.currentPlayerMarker());
            this.isWinner();
            this.switchPlayer();
        };

        Board.prototype.currentPlayerMarker = function() {
            return marker[this.currentPlayer];
        }

        Board.prototype.switchPlayer = function() {
            this.currentPlayer = 1 - this.currentPlayer;
        }

        return Board;
    })();

    angular.module('ngOughts').factory('Board', function(){
        return Board;
    });

}).call(this);



app.controller('BoardCtrl', function($scope, Board){
    $scope.board = new Board;
});