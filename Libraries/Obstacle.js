function Obstacle(type, roadNum, bitmap){
	var self = this;
	base(self,LSprite,[]);
    self.kind = 1;
    self.speed = 0;

    self.type = type;
    self.roadNum = roadNum;


    switch(type){
        case 0:
            //6个小桶
            switch(roadNum[0]){
                case 0:
                    self.x = 45;
                    self.y = -bitmap.getHeight();
                    break;

                case 1:
                    self.x = 106;
                    self.y = -bitmap.getHeight();
                    break;

                case 2:
                    self.x = 167;
                    self.y = -bitmap.getHeight();
                    break;

                case 3:
                    self.x = 227;
                    self.y = -bitmap.getHeight();
                    break;

                case 4:
                    self.x = 289;
                    self.y = -bitmap.getHeight();
                    break;

                case 5:
                    self.x = 347;
                    self.y = -bitmap.getHeight();
                    break;
            }
            break;

        case 1:
            switch(roadNum[0]){
                case 0:
                    self.x = 37;
                    self.y = -bitmap.getHeight();
                    break;

                case 1:
                    self.x = 100;
                    self.y = -bitmap.getHeight();
                    break;

                case 2:
                    self.x = 161;
                    self.y = -bitmap.getHeight();
                    break;

                case 3:
                    self.x = 222;
                    self.y = -bitmap.getHeight();
                    break;

                case 4:
                    self.x = 283;
                    self.y = -bitmap.getHeight();
                    break;

                case 5:
                    self.x = 342;
                    self.y = -bitmap.getHeight();
                    break;

                case 6:
                    self.x = 399;
                    self.y = -bitmap.getHeight();
                    break;
            }
            break;

        case 2:
            switch(roadNum[0]){
                case 0:
                    self.x = 33;
                    self.y = -bitmap.getHeight();
                    break;

                case 1:
                    self.x = 96;
                    self.y = -bitmap.getHeight();
                    break;

                case 2:
                    self.x = 157;
                    self.y = -bitmap.getHeight();
                    break;

                case 3:
                    self.x = 219;
                    self.y = -bitmap.getHeight();
                    break;

                case 4:
                    self.x = 278;
                    self.y = -bitmap.getHeight();
                    break;

                case 5:
                    self.x = 339;
                    self.y = -bitmap.getHeight();
                    break;

                case 6:
                    self.x = 396;
                    self.y = -bitmap.getHeight();
                    break;
            }
            break;

        case 3:
            switch(roadNum[0]){
                case 0:
                    self.x = 38;
                    self.y = -bitmap.getHeight();
                    break;

                case 1:
                    self.x = 100;
                    self.y = -bitmap.getHeight();
                    break;

                case 2:
                    self.x = 162;
                    self.y = -bitmap.getHeight();
                    break;

                case 3:
                    self.x = 222;
                    self.y = -bitmap.getHeight();
                    break;

                case 4:
                    self.x = 283;
                    self.y = -bitmap.getHeight();
                    break;

                case 5:
                    self.x = 342;
                    self.y = -bitmap.getHeight();
                    break;

                case 6:
                    self.x = 400;
                    self.y = -bitmap.getHeight();
                    break;
            }
            break;
    }

    self.addChild(bitmap);
}
Obstacle.prototype.logic = function(myMoveSpeed){
	var self = this;
    self.y = self.y + myMoveSpeed;
    if(self.y > LGlobal.height){
           return true;
    }
    else{
        return false;
    }
};
