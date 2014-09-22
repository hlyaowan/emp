function AiCar(type, roadNum, speed, aiLevel, bitmap){
	var self = this;
	base(self,LSprite,[]);
    self.kind = 0;

    var centerDisX = 0;
    var centerDisY = 0;
    var roadStartX = 46;

    switch(type){
        case 0:
            centerDisX = 12;
            centerDisY = 58;
            break;

        case 1:
            centerDisX = 16;
            centerDisY = 70;
            break;

        case 2:
            centerDisX = 17;
            centerDisY = 67;
            break;

        case 3:
            centerDisX = 15;
            centerDisY = 62;
            break;

        case 4:
            centerDisX = 14;
            centerDisY = 65;
            break;

        case 5:
            centerDisX = 18;
            centerDisY = 74;
            break;

        case 6:
            centerDisX = 15;
            centerDisY = 68;
            break;

        case 7:
            centerDisX = 15;
            centerDisY = 68;
            break;

        case 8:
            centerDisX = 15;
            centerDisY = 97;
            break;

        case 9:
            centerDisX = 26;
            centerDisY = 131;
            break;

        case 10:
            centerDisX = 24;
            centerDisY = 188;
            break;

        case 11:
            centerDisX = 20;
            centerDisY = 88;
            break;

        case 12:
            centerDisX = 16;
            centerDisY = 90;
            break;

        case 13:
            centerDisX = 24;
            centerDisY = 99;
            break;

        case 14:
            centerDisX = 16;
            centerDisY = 74;
            break;

        case 15:
            centerDisX = 18;
            centerDisY = 72;
            break;

        case 16:
            centerDisX = 16;
            centerDisY = 72;
            break;

        case 17:
            centerDisX = 16;
            centerDisY = 66;
            break;

        case 18:
            centerDisX = 16;
            centerDisY = 66;
            break;

        case 19:
            centerDisX = 16;
            centerDisY = 74;
            break;

        case 20:
            centerDisX = 15;
            centerDisY = 72;
            break;
    }
    self.centerDisX = centerDisX;
    self.centerDisY = centerDisY;

    bitmap.x = -centerDisX;
    bitmap.y = -centerDisY;
    self.addChild(bitmap);
    self.roadNum = roadNum;
    self.x = roadStartX+58*roadNum+centerDisX;
    self.y = -(bitmap.getHeight()-centerDisY);

    self.speed = speed;
    self.aiLevel = aiLevel;

    self.isTurnLeft = false;
    self.isTurnRight = false;

    self.turnRotateCount = 0;
    self.turnRotateTotal = 30;

    self.moveCount = 0;
    self.MaxMovment = 0;
}

AiCar.prototype.kickLeft = function(){
    var self = this;
    self.isTurnLeft = true;
    self.isTurnRight = false;

    self.turnRotateCount = 0;
    self.turnRotateTotal = 30;

    self.rightBackDirect();

    self.moveCount = 0;
    self.MaxMovment = 58;

    self.roadNum--;
};

AiCar.prototype.kickRight = function(){
    var self = this;
    self.isTurnLeft = false;
    self.isTurnRight = true;

    self.turnRotateCount = 0;
    self.turnRotateTotal = 30;

    self.leftBackDirect();

    self.moveCount = 0;
    self.MaxMovment = 58;

    self.roadNum++;
};

AiCar.prototype.leftBackDirect = function(){
    var self = this;
    self.isTurnLeft = false;
    self.rotate = 0;
};

AiCar.prototype.rightBackDirect = function(){
    var self = this;
    self.isTurnRight = false;
    self.rotate = 0;
};

AiCar.prototype.logic = function(mapMoveSpeed){
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

//    console.log("self.x:"+self.x);
//    console.log("self.y:"+self.y);

    self.y = self.y + (mapMoveSpeed - self.speed);
    if(self.y > LGlobal.height){
        return true;
    }
    else{
        return false;
    }
};
