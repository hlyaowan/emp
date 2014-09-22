function Tool(type, roadNum, img){
	var self = this;
	base(self,LSprite,[]);
    self.kind = 2;
    self.speed = 0;

    self.type = type;
    self.roadNum = roadNum;


    switch(type){
        case 0:
            self.frameCount = 5;
            var list = LGlobal.divideCoordinate(4*50,1*50,1,4);
            var data = new LBitmapData(img,0,0,50,50);
            self.anime = new LAnimation(self,data,list);

            //金币
            switch(roadNum){
                case 0:
                    self.x = 37;
                    self.y = -50;
                    break;

                case 1:
                    self.x = 100;
                    self.y = -50;
                    break;

                case 2:
                    self.x = 161;
                    self.y = -50;
                    break;

                case 3:
                    self.x = 222;
                    self.y = -50;
                    break;

                case 4:
                    self.x = 283;
                    self.y = -50;
                    break;

                case 5:
                    self.x = 342;
                    self.y = -50;
                    break;

                case 6:
                    self.x = 399;
                    self.y = -50;
                    break;
            }
            break;

        case 2:
            switch(roadNum){
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
            switch(roadNum){
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

//    self.addChild(bitmap);
}
Tool.prototype.logic = function(myMoveSpeed){
	var self = this;

    if(self.type == 0){
        self.frameCount--;
        if(self.frameCount<0){
            self.anime.onframe();
            self.frameCount = 5;
        }
    }

    self.y = self.y + myMoveSpeed;
    if(self.y > LGlobal.height){
           return true;
    }
    else{
        return false;
    }
};
