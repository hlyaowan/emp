function LogoView(){
	base(this,LView,[]);
}
function LogoViewfeffefe(){
    console.log("hahahha");
}

LogoView.prototype.construct=function(){
	var self = this;
    //背景
	var bitmapBgBack = new LBitmap(new LBitmapData(LMvc.datalist["logo_bg"]));
    bitmapBgBack.alpha = 0;
	self.addChild(bitmapBgBack);
	self.bitmapBgBack = bitmapBgBack;
    //start按钮
    var bitmapStart = new LBitmap(new LBitmapData(LMvc.datalist["logo_start"]));
	var layerStart = new LSprite();
    layerStart.addChild(bitmapStart);
    layerStart.x = LGlobal.width-bitmapStart.getWidth()>>1;
    layerStart.y = LGlobal.height-200;
    layerStart.visible = false;
	self.addChild(layerStart);
	self.layerStart = layerStart;
    // 移动的汽车
    var bitmapCar = new LBitmap(new LBitmapData(LMvc.datalist["logo_move"]));
    bitmapCar.x = -bitmapCar.getWidth()*0.5;
    bitmapCar.y = -bitmapCar.getHeight()*0.5;
    var layerCar = new LSprite();
    layerCar.x = LGlobal.width+bitmapCar.getWidth()*0.5;
    layerCar.y = 100;
    layerCar.scaleX = layerCar.scaleY = 0.2;
    layerCar.addChild(bitmapCar);
    self.addChild(layerCar);
    self.layerCar = layerCar;
    //标题
	var bitmapTitle = new LBitmap(new LBitmapData(LMvc.datalist["logo_title"]));
	bitmapTitle.x = -bitmapTitle.getWidth()*0.5;
	bitmapTitle.y = -bitmapTitle.getHeight()*0.5;
	var layerTitle = new LSprite();
	layerTitle.x = LGlobal.width*0.5;
	layerTitle.y = 150;
	self.addChild(layerTitle);
	layerTitle.scaleX = layerTitle.scaleY = 3;
	layerTitle.alpha = 0;
	layerTitle.addChild(bitmapTitle);
	self.layerTitle = layerTitle;
};
LogoView.prototype.insertWindow=function(){
	var self = this;

	self.childWindow = new LSprite();
	self.childWindow.x = LGlobal.width*0.5;
	self.childWindow.y = LGlobal.height*0.5;
	self.addChild(self.childWindow);
	
	self.childWindowLayer  = new LSprite();
	self.childWindow.addChild(self.childWindowLayer);
	self.childWindowLayer.x = -200;
	self.childWindowLayer.y = -200;
	
	var childBack = new LSprite();
	childBack.graphics.drawRect(0,"#000000",[0,0,400,400],true,"#000000");
	self.childWindowLayer.addChild(childBack);
	childBack.alpha = 0.7;
	
	var childBar = new Bar(400,400);
	self.childWindowLayer.addChild(childBar);
	
	
	var titleLabel = new LTextField();
	titleLabel.text = "ログイン / 新規登録";
	titleLabel.color = "#FFFFFF";
	titleLabel.size = 25;
	titleLabel.weight = "bolder";
	self.childWindowLayer.addChild(titleLabel);
	titleLabel.x = (400 - titleLabel.getWidth())*0.5;
	titleLabel.y = 40;
	
	var textLayer;
	textLayer = new LBitmap(new LBitmapData(LMvc.datalist["inputbox-1"]));
	self.childWindowLayer.addChild(textLayer);
	textLayer.x = (400-textLayer.getWidth())*0.5;
	textLayer.y = 120;
	
	var labelLayer;
	labelLayer = new LSprite();
	labelLayer.graphics.drawRect(1,"#000000",[0,0,270,40],true,"#FFFFFF");
	labelLayer.alpha = 0;
	var nameText = new LTextField();
	nameText.x = textLayer.x + 35;
	nameText.y = textLayer.y + 20;
	nameText.setType(LTextFieldType.INPUT,labelLayer);
	self.childWindowLayer.addChild(nameText);
	var nameLabel = new LTextField();
	nameLabel.text = "名前";
	nameLabel.color = "#FFFFFF";
	nameLabel.size = 20;
	nameLabel.weight = "bolder";
	self.childWindowLayer.addChild(nameLabel);
	nameLabel.x = 60;
	nameLabel.y = 100;
	
	textLayer = new LBitmap(new LBitmapData(LMvc.datalist["inputbox-1"]));
	self.childWindowLayer.addChild(textLayer);
	textLayer.x = (400-textLayer.getWidth())*0.5;
	textLayer.y = 220;
	
	labelLayer = new LSprite();
	labelLayer.graphics.drawRect(1,"#000000",[0,0,220,40],true,"#FFFFFF");
	labelLayer.alpha = 0;
	var passText = new LTextField();
	passText.displayAsPassword = true;
	passText.x = textLayer.x + 35;
	passText.y = textLayer.y + 20;
	passText.setType(LTextFieldType.INPUT,labelLayer);
	self.childWindowLayer.addChild(passText);
	var passLabel = new LTextField();
	passLabel.text = "パスワード";
	passLabel.color = "#FFFFFF";
	passLabel.size = 20;
	passLabel.weight = "bolder";
	self.childWindowLayer.addChild(passLabel);
	passLabel.x = 60;
	passLabel.y = 205;
	
	var buttonLogin = new LButtonSample2("ログイン");
	buttonLogin.backgroundCorl = "blue";
	buttonLogin.x = 70;
	buttonLogin.y = 300;
	self.childWindowLayer.addChild(buttonLogin);
	
	var buttonInsert = new LButtonSample2("新規登録");
	buttonInsert.backgroundCorl = "blue";
	buttonInsert.x = 220;
	buttonInsert.y = 300;
	self.childWindowLayer.addChild(buttonInsert);
	
	
	buttonLogin.addEventListener(LMouseEvent.MOUSE_UP, function(event){
		self.controller.toLogin(nameText.text,passText.text);
	});
	buttonInsert.addEventListener(LMouseEvent.MOUSE_UP,function(event){
		self.controller.toInsert(nameText.text,passText.text);
	});
};