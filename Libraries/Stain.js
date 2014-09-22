function Stain(x,y){
	var self = this;
	base(self,LSprite,[]);
    self.x = x;
    self.y = y;
    self.addChild(new LBitmap(new LBitmapData(dataList["road_stain"])));
}
Stain.prototype.logic = function(myMoveSpeed){
	var self = this;
    self.y = self.y + myMoveSpeed;
    if(self.y > LGlobal.height){
           return true;
    }
    else{
        return false;
    }
};
