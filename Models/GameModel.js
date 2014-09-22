function GameModel(){
	base(this,MyModel,[]);
}
GameModel.prototype.getCommonImages=function(){
	var list = [
        {name:"road_bg",path:"./imgs/road_bg.jpg"},
        {name:"road_line",path:"./imgs/road_line.png"},
        {name:"road_stain",path:"./imgs/road_stain.png"},
        {name:"road_obstacle_0",path:"./imgs/road_obstacle_0.png"},
        {name:"road_obstacle_1",path:"./imgs/road_obstacle_1.png"},
        {name:"road_obstacle_2",path:"./imgs/road_obstacle_2.png"},
        {name:"road_obstacle_3",path:"./imgs/road_obstacle_3.png"},
        {name:"mycar_0",path:"./imgs/mycar_0.png"},
        {name:"npc0",path:"./imgs/npc/npc0.png"},
        {name:"npc1",path:"./imgs/npc/npc1.png"},
        {name:"npc2",path:"./imgs/npc/npc2.png"},
        {name:"npc3",path:"./imgs/npc/npc3.png"},
        {name:"npc4",path:"./imgs/npc/npc4.png"},
        {name:"npc5",path:"./imgs/npc/npc5.png"},
        {name:"npc6",path:"./imgs/npc/npc6.png"},
        {name:"npc7",path:"./imgs/npc/npc7.png"},
        {name:"npc8",path:"./imgs/npc/npc8.png"},
        {name:"npc9",path:"./imgs/npc/npc9.png"},
        {name:"npc10",path:"./imgs/npc/npc10.png"},
        {name:"npc11",path:"./imgs/npc/npc11.png"},
        {name:"npc12",path:"./imgs/npc/npc12.png"},
        {name:"npc13",path:"./imgs/npc/npc13.png"},
        {name:"npc14",path:"./imgs/npc/npc14.png"},
        {name:"npc15",path:"./imgs/npc/npc15.png"},
        {name:"npc16",path:"./imgs/npc/npc16.png"},
        {name:"npc17",path:"./imgs/npc/npc17.png"},
        {name:"npc18",path:"./imgs/npc/npc18.png"},
        {name:"npc19",path:"./imgs/npc/npc19.png"},
        {name:"npc20",path:"./imgs/npc/npc20.png"},
        {name:"tool0",path:"./imgs/tool0.png"}
	];
	return list;
};