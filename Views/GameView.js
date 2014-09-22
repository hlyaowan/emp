var systemTime;

var myMoveDistance;
var needSpeedUp = true;
var myMoveSpeed, myMaxMoveSpeed = 20, SpeedUpSpace = 1, SpeedDownSpace;
var speedUpNeedTimeTotal = 500, speedDownNeedTimeTotal;
var speedChangeNeedTimeCount = 0;
var backgroundLayer,myCarSprite;

var stainCount = 1;
var aiCarCount = 1;
var toolCount = 1;
var roadList = [[],[],[],[],[],[],[]];//道路2维数组，第1维道路index，第2维存放障碍物
var toolArray = new Array();//存放道具

var lastCreateAiRoad = -1;
//var lastCreateAiCenterY;

function GameView(){
	base(this,LView,[]);
}
GameView.prototype.construct=function(){
};

GameView.prototype.init=function(){
    var self = this;

    backgroundLayer = new LSprite();
    self.addChild(backgroundLayer);

    var upBitmap = new LBitmap(new LBitmapData(LMvc.datalist["road_bg"]));
    var downBitmap = new LBitmap(new LBitmapData(LMvc.datalist["road_bg"]));
    upBitmap.y = -LGlobal.height;

    backgroundLayer.upBitmap = upBitmap;
    backgroundLayer.addChild(upBitmap);
    backgroundLayer.downBitmap = downBitmap;
    backgroundLayer.addChild(downBitmap);

    var carBitmap = new LBitmap(new LBitmapData(LMvc.datalist["mycar_0"]));
    myCarSprite = new MyCar(LGlobal.width - carBitmap.getWidth()>>1, LGlobal.height - 100, carBitmap);
    backgroundLayer.addChild(myCarSprite);

    myMoveDistance = 0;
    myMoveSpeed = 1;
    speedChangeNeedTimeCount = 0;
    stainCount = 1;
    aiCarCount = 1;
    systemTime = Date.now();
    toolArray.splice(0,toolArray.length);

    backgroundLayer.addEventListener(LEvent.ENTER_FRAME, gameLogic);
//    backgroundLayer.addEventListener(LMouseEvent.MOUSE_DOWN, pointPress);
    backgroundLayer.addEventListener(LMouseEvent.MOUSE_UP, pointRelease);
};


function gameLogic(){
    var nowTime = Date.now();
    //上一次逻辑消耗的时间
    var deltaTime = (nowTime - systemTime);
    systemTime = nowTime;

    myMoveDistance = myMoveDistance + myMoveSpeed;

    //创建道具
    if(myMoveDistance >= toolCount*200){
        toolCount++;
        createTool();
    }
    //创建一个aiCar
    if(myMoveDistance >= aiCarCount*400){
        aiCarCount++;
        createAiCar();
    }
    //创建一个障碍物
    if(myMoveDistance >= stainCount*1000){
        stainCount++;
//        createObstacle();
    }
    //tool动逻辑
    for(var i=0; i < toolArray.length; i++){
            var temp = toolArray[i];
            if(null != temp){
                //检查和myCar的碰撞
                if(temp.roadNum == myCarSprite.roadNum){

                }
                if(temp.logic(myMoveSpeed)){
                    toolArray.splice(i,1);
                    i = i-1;

                    backgroundLayer.removeChild(temp);
                    temp = null;
                }
            }
            else{
                toolArray.splice(i,1);
                i = i-1;
            }
    }
    //障碍物移动逻辑
    for(var i=0; i < roadList.length; i++){
        for(var j=0; j < roadList[i].length; j++){
            var temp = roadList[i][j];
            if(null != temp){
                //判断前面是否有障碍物
                if(j+1 < roadList[i].length){
                    //存在障碍物
                    var tempFront = roadList[i][j+1];
                    if(temp.speed>tempFront.speed){
                        //根据距离减速
                        var disRel = temp.y - tempFront.y - temp.centerDisY;
                        if(disRel >= 300){
                            temp.speed = temp.speed-0.1;
                        }
                        else if(disRel >= 200){
                            temp.speed = temp.speed-0.5;
                        }
                        else if(disRel >= 10){
                            temp.speed = temp.speed-1;
                        }
                        else if(disRel>0){
                            temp.speed = tempFront.speed;
                        }
                        else{
                            //相撞
                        }
                    }
                    else if(temp.speed <= tempFront.speed){
                        var disRel = temp.y - tempFront.y - temp.centerDisY;
                        if(disRel < 0){
                            //相撞
                        }
                    }
                }

                if(temp.logic(myMoveSpeed)){
                    roadList[i].splice(j,1);
                    j = j-1;

                    backgroundLayer.removeChild(temp);
                    temp = null;
                }
            }
            else{
                roadList[i].splice(j,1);
                j = j-1;
            }
        }
    }

    //判断当前车道前面是否有障碍物    变道  减速
//    var hasObstacle = false;
//    var temp = roadList[myCarSprite.roadNum];
//    for(var j=0; j < temp.length; j++){
//        var dis = myCarSprite.y - temp[j].y;
//        //在0-500范围内认为出现了障碍物
//        if(dis > 0 && dis < 600){
//            if(dis >= 300){
//                //距离300以上  选择  变道  或者  减速
//                var randNum = Math.random();
////              var randNum = 0.7;
//                if(randNum >= 0.5 && (!myCarSprite.isTurnLeft && !myCarSprite.isTurnRight)){
//                    //变道
//                    turnToOtherRoad();
//                }
//                else{
//                    //减速
//                    needSpeedUp = false;
//                    speedDownNeedTimeTotal = 200;
//                    SpeedDownSpace = 2;
//                }
//            }
//            else if(dis < 300){
//                //距离300以下    变道  加  减速
//                //变道
//                if(!myCarSprite.isTurnLeft && !myCarSprite.isTurnRight){
//                    //变道
//                    turnToOtherRoad();
//                }
//                //根据距离大小控制 减速 强度
//                needSpeedUp = false;
//                speedDownNeedTimeTotal = 100;
//                SpeedDownSpace = 3;
//            }
//
//            hasObstacle = true;
//            break;
//        }
//    }
//    if(!hasObstacle){
//        //加速
//        needSpeedUp = true;
//    }

    mapMove();
    if(needSpeedUp){
        myCarSpeedUp(deltaTime);
    }
    else{
        myCarSpeedDown(deltaTime);
    }
    myCarSprite.logic();
}

function mapMove(){
    backgroundLayer.upBitmap.y = backgroundLayer.upBitmap.y + myMoveSpeed;
    backgroundLayer.downBitmap.y = backgroundLayer.downBitmap.y + myMoveSpeed;

    var upDecrease = backgroundLayer.upBitmap.y -LGlobal.height;
    if(backgroundLayer.upBitmap.y >= LGlobal.height){
        backgroundLayer.upBitmap.y = -LGlobal.height + upDecrease;
    }

    var downDecrease = backgroundLayer.downBitmap.y - LGlobal.height;
    if(backgroundLayer.downBitmap.y >= LGlobal.height){
        backgroundLayer.downBitmap.y = -LGlobal.height + downDecrease;
    }
}

function turnToOtherRoad(){
    //躲避障碍物
    if(myCarSprite.roadNum == 0){
        //右转
        myCarSprite.kickRight();
    }
    else if(myCarSprite.roadNum == 6){
        //左转
        myCarSprite.kickLeft();
    }
    else{
        //判断左边车道上是否有障碍物
        var isLeftHasObstacle = false;
        var disLeft = 0;
        var tempLeft = roadList[myCarSprite.roadNum-1];
//                    console.log("左边车道数量:"+tempLeft.length);
        for(var k=0; k < tempLeft.length; k++){
            disLeft = myCarSprite.y - tempLeft[k].y;
            if(disLeft > 0){
                isLeftHasObstacle = true;
                break;
            }
//                        if(myCarSprite.y - tempLeft[k].y < 300){
//                            //左边有障碍物   右转
//                            myCarSprite.kickRight();
//                            turnLeft = false;
//                            break;
//                        }
        }

        //判断右边车道上是否有障碍物
        var isRightHasObstacle = false;
        var disRight = 0;
        var tempRight = roadList[myCarSprite.roadNum+1];
//                    console.log("右边车道数量:"+tempLeft.length);
        for(var l=0; l < tempRight.length; l++){
            disRight = myCarSprite.y - tempRight[l].y;
            if(disRight > 0){
                isRightHasObstacle = true;
                break;
            }
        }

        if(isLeftHasObstacle){
            if(isRightHasObstacle){
                //比距离
                if(disLeft < disRight){
                    myCarSprite.kickRight();
                }
                else if(disLeft > disRight){
                    myCarSprite.kickLeft();
                }
                else{
                    var randNum = Math.random();
                    if(randNum >= 0.5){
                        myCarSprite.kickLeft();
                    }
                    else{
                        myCarSprite.kickRight();
                    }
                }
            }
            else{
                myCarSprite.kickRight();
            }
        }
        else{
            if(isRightHasObstacle){
                myCarSprite.kickLeft();
            }
            else{
                var randNum = Math.random();
                if(randNum >= 0.5){
                    myCarSprite.kickLeft();
                }
                else{
                    myCarSprite.kickRight();
                }
            }
        }
    }
}

function myCarSpeedUp(deltaTime){
    if(myMoveSpeed < myMaxMoveSpeed){
        speedChangeNeedTimeCount = speedChangeNeedTimeCount + deltaTime;
        if(speedChangeNeedTimeCount >= speedUpNeedTimeTotal){
            speedChangeNeedTimeCount = speedChangeNeedTimeCount - speedUpNeedTimeTotal;

            myMoveSpeed = myMoveSpeed + SpeedUpSpace;
        }
    }
}

function myCarSpeedDown(deltaTime){
    if(myMoveSpeed > 1){
        speedChangeNeedTimeCount = speedChangeNeedTimeCount + deltaTime;
        if(speedChangeNeedTimeCount >= speedDownNeedTimeTotal){
            speedChangeNeedTimeCount = speedChangeNeedTimeCount - speedDownNeedTimeTotal;

            myMoveSpeed = myMoveSpeed - SpeedDownSpace;
            if(myMoveSpeed < 1){
                myMoveSpeed = 1;
            }
        }
    }
}

function pointPress(event){
    var centerX = LGlobal.width>>1;
    if(event.offsetX >= centerX){
        myCarSprite.kickRight();
    }
    else{
        myCarSprite.kickLeft();
    }
}

function pointRelease(event){
    var centerX = LGlobal.width>>1;
    if(event.offsetX >= centerX){
//        myCarSprite.rightBackDirect();

        if(!myCarSprite.isTurnLeft && !myCarSprite.isTurnRight && myCarSprite.roadNum<6){
            myCarSprite.kickRight();
        }
    }
    else{
//        myCarSprite.leftBackDirect();

        if(!myCarSprite.isTurnLeft && !myCarSprite.isTurnRight && myCarSprite.roadNum>0){
            myCarSprite.kickLeft();
        }
    }
}

function createTool(){
    var type = 0;
    var roadNum = randomNextInt(0,6);
    var tempTool = new Tool(type, roadNum, LMvc.datalist["tool"+type]);
//    if(lastCreateAiRoad == roadNum){
//        var tempLastCar = roadList[roadNum][roadList[roadNum].length-1];
//        var dis = tempLastCar.y - tempAiCar.y - tempLastCar.centerDisY;
//        if(dis <= 0){
//            tempAiCar.y = tempAiCar.y - (10-dis);
//        }
//    }


//    lastCreateAiRoad = roadNum;

    toolArray.push(tempTool);
//    roadList[roadNum].push(tempAiCar);
    backgroundLayer.addChild(tempTool);
}

function createAiCar(){
    var type = randomNextInt(0,20);
    var bitmap = new LBitmap(new LBitmapData(LMvc.datalist["npc"+type]));
    var roadNum = randomNextInt(0,6);
    var tempAiCar = new AiCar(type,roadNum,randomNextInt(9,16),1,bitmap);
    if(lastCreateAiRoad == roadNum){
        var tempLastCar = roadList[roadNum][roadList[roadNum].length-1];
        var dis = tempLastCar.y - tempAiCar.y - tempLastCar.centerDisY;
        if(dis <= 0){
            tempAiCar.y = tempAiCar.y - (10-dis);
        }
    }


    lastCreateAiRoad = roadNum;

    roadList[roadNum].push(tempAiCar);
    backgroundLayer.addChild(tempAiCar);
}

function createObstacle(){
    //      var type = randomNextInt(0,3);
    var type = 0;
    var roadNum = new Array();
    if(type == 0){
        roadNum[0] = randomNextInt(0,5);
//          roadNum[0] = 3;
        roadNum[1] = roadNum[0] + 1;
    }
    else{
        roadNum[0] = randomNextInt(0,6);
    }
    var bitmap = new LBitmap(new LBitmapData(LMvc.datalist["road_obstacle_"+type]));
    var tempStain = new Obstacle(type,roadNum,bitmap);

    if(type == 0){
        roadList[roadNum[0]].push(tempStain);
        roadList[roadNum[1]].push(tempStain);
    }
    else{
        roadList[roadNum[0]].push(tempStain);
    }
    backgroundLayer.addChild(tempStain);
}