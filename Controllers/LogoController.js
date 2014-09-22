function LogoController(){
	base(this,MyController,[]);
}
LogoController.prototype.construct=function(){
	var self = this;
	self.loadBackMusic();
	LTweenLite.to(self.view.bitmapBgBack,0.2,{alpha:1});
	LTweenLite.to(self.view.layerCar,0.5,{x:LGlobal.width*0.5,y:LGlobal.height*0.5,scaleX:1,scaleY:1,onComplete:self.run})
	.to(self.view.layerTitle,1,{alpha:1,scaleX:1,scaleY:1,ease:Elastic.easeOut});
};
LogoController.prototype.loadBackMusic=function(){
	var self = this;
	var backMusicUrl = self.model.getBackMusic();
	var backMusic = new LSound();
	backMusic.addEventListener(LEvent.COMPLETE,self.playBackMusic);
	backMusic.load(backMusicUrl);
};
LogoController.prototype.playBackMusic = function(event){
	var sound = event.currentTarget;
	sound.play();
};

LogoController.prototype.checkAuth=function(obj){
	var self = obj.parent.controller;
	self.model.getStatus(self.addEvent);
};
LogoController.prototype.addEvent=function(){
	var self = this;
	self.view.layerStart.addEventListener(LMouseEvent.MOUSE_UP, self.onClick);
};

LogoController.prototype.onClick = function(event){
    var self = event.clickTarget.parent.controller;
    self.view.layerStart.removeEventListener(LMouseEvent.MOUSE_UP, self.onClick);
//    if(!self.model.userStatus){
//        var list = self.model.getCommonImages();
//        self.load.image(list,self.libraryLoad);
//    }else{
        self.gameStart(true);
//    }
};

LogoController.prototype.run=function(obj){
	var self = obj.parent.controller;
//	LTweenLite.to(self.view.layerChara,2,{y:LGlobal.height - 200})
//	.to(self.view.layerChara,2,{y:LGlobal.height - 220,onComplete:self.run});
    self.view.layerStart.visible = true;
    self.view.layerStart.addEventListener(LMouseEvent.MOUSE_UP, self.onClick);
};

LogoController.prototype.libraryLoad=function(){
	var self = this;
	self.load.library("Bar",self.insertWindow);
};

LogoController.prototype.gameStart=function(result,msg){
	var self =this;
//	if(!result){
//		alert(msg);
//		return;
//	}
	LTweenLite.removeAll();
	self.view.parent.controller.gameLoad();
};
LogoController.prototype.insertWindow = function(){
	var self = this;
	self.view.insertWindow();
};
LogoController.prototype.toLogin=function(name,pass){
	var self = this;
	self.model.login(name,pass,self.gameStart);
};
LogoController.prototype.toInsert=function(name,pass){
	var self = this;
	self.model.insert(name,pass,self.gameStart);
};