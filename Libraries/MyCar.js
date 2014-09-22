function MyCar(x,y,bitmap){
	var self = this;
	base(self,LSprite,[]);

    var centerDisX = 20;
    var centerDisY = 75;

    self.roadNum = 3;

    self.centerDisX = centerDisX;
    self.centerDisY = centerDisY;
    self.x = 228+centerDisX;
    self.y = 590+centerDisY;
    bitmap.x = -centerDisX;
    bitmap.y = -centerDisY;
    self.addChild(bitmap);

    self.isTurnLeft = false;
    self.isTurnRight = false;

    self.turnRotateCount = 0;
    self.turnRotateTotal = 30;

    self.moveCount = 0;
    self.MaxMovment = 0;
}

MyCar.prototype.kickLeft = function(){
    var self = this;
    self.isTurnLeft = true;
    self.isTurnRight = false;

    self.turnRotateCount = 0;
    self.turnRotateTotal = 30;

    self.rightBackDirect();

    self.moveCount = 0;
    self.MaxMovment = 60;

    self.roadNum--;
};

MyCar.prototype.kickRight = function(){
    var self = this;
    self.isTurnLeft = false;
    self.isTurnRight = true;

    self.turnRotateCount = 0;
    self.turnRotateTotal = 30;

    self.leftBackDirect();

    self.moveCount = 0;
    self.MaxMovment = 60;

    self.roadNum++;
};

MyCar.prototype.leftBackDirect = function(){
    var self = this;
    self.isTurnLeft = false;
    self.rotate = 0;
};

MyCar.prototype.rightBackDirect = function(){
    var self = this;
    self.isTurnRight = false;
    self.rotate = 0;
};

MyCar.prototype.logic = function(){
	var self = this;
    if(self.isTurnLeft){
        if(self.turnRotateCount < self.turnRotateTotal){
            self.turnRotateCount = self.turnRotateCount + 10;
        }
        self.rotate = -self.turnRotateCount;
        self.x = self.x - 8;

        self.moveCount = self.moveCount + 8;
        if(self.moveCount >= self.MaxMovment){
            self.leftBackDirect();
        }
    }
    else if(self.isTurnRight){
        if(self.turnRotateCount < self.turnRotateTotal){
            self.turnRotateCount = self.turnRotateCount + 10;
        }
        self.rotate = self.turnRotateCount;
        self.x = self.x + 8;

        self.moveCount = self.moveCount + 8;
        if(self.moveCount >= self.MaxMovment){
            self.rightBackDirect();
        }
    }
};
