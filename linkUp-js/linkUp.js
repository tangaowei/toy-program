var imageArray = [
"da.jpg",
"dong.jpg",
"guan.jpg",
"huo.jpg",
"sheng.jpg",
"shu.jpg",
"tu.jpg",
"xin.jpg",
"xue.jpg",
"zhong.jpg",
"yuan.jpg",
"qing.jpg"
];
var defaultImg = "images/default.jpg";
var nPics = imageArray.length; // number of pictures
var nRow = 12; // number of rows
var nCol = 12; // number of cols
var picModel;
var NOPIC = -1;

var link_x1 = -1;
var link_y1 = -1;

// used for draw
var verticalPosArray;
var horizonPosArray;
var canvas1;
var canvas2;

function createModel(row, col, npic) {
    var model = new Array(row);
    for(var i = 0; i < row; ++i)
	model[i] = new Array(col);
    
    var radArr = randomArray(row * col, npic);
    for(var i = 0; i < row; ++i)
	for(var j = 0; j < col; ++j)
	    model[i][j] = radArr[i * row + j];
    return model;
}

function randomArray(length, npics) {
    var radarr = new Array(length);
    if(length % 2 != 0) {
	alert("Error: length is not even, in randomArray()");
	return;
    }
    for(var i = 0; i < length; i++) {
	radarr[i] = i % npics;
    }
    
    for(var i = 0; i < length; ++i) {
	var index = parseInt(Math.random() * length);
	if(index == length) {
	    alert("Error: index == length, in randomArray()");
	    --index;
	}
	var tmp = radarr[i];
	radarr[i] = radarr[index];
	radarr[index] = tmp;
    }
    return radarr;
}

function findPath(xx1, yy1, xx2, yy2) {
    var row = nRow + 2;
    var col = nCol + 2;
    var copyModel = new Array(row);
    for(var i = 0; i < row; ++i)
	copyModel[i] = new Array(col);
    for(var i = 0; i < row; ++i)
	for(var j = 0; j < col; ++j)
	    if(i == 0 || j == 0 || i == row - 1 || j == col - 1)
		copyModel[i][j] = NOPIC;
            else
		copyModel[i][j] = picModel[i-1][j-1];

    var turns = new Array(row);
    var prev = new Array(row);
    for(var i = 0; i < row; ++i) {
	turns[i] = new Array(col);
	prev[i] = new Array(col);
    }
    for(var i = 0; i < row; ++i)
	for(var j = 0; j < col; ++j) {
	    turns[i][j] = parseInt(0);
	    prev[i][j] = parseInt(-1);
	}
     

    var x1 = parseInt(xx1) + 1;
    var y1 = parseInt(yy1) + 1;
    var x2 = parseInt(xx2) + 1;
    var y2 = parseInt(yy2) + 1;
    var qx = new Queue();
    var qy = new Queue();
    qx.enqueue(x1);
    qy.enqueue(y1);

    prev[x1][y1] = 0;
    while(!qx.isEmpty()) {
	var cx = qx.dequeue();
	var cy = qy.dequeue();
	for(var i = 0; i < 4; ++i) {
	    var nx = cx, ny = cy;
	    while(true) {
		if(i == 0) {
		    nx++;
		    if(nx >= row) break;
		} else if(i == 1) {
		    nx--;
		    if(nx < 0) break;
		} else if(i == 2) {
		    ny++;
		    if(ny >= col) break;
		} else if(i == 3) {
		    ny--;
		    if(ny < 0) break;
		}
		if(nx == x2 && ny == y2) {
		    prev[x2][y2] = cx * row + cy;
		    return prev;
		}
		if(copyModel[nx][ny] != NOPIC) break;
		if(turns[cx][cy] >= 2) continue;
		if(prev[nx][ny] != -1) break;
		prev[nx][ny] = cx * row + cy;
		turns[nx][ny] = turns[cx][cy] + 1;
		qx.enqueue(nx);
		qy.enqueue(ny);
	    }
	}
    }
    return [];
}

function drawPanel(panelId, R, C) {
    var pics = document.getElementById(panelId);
    verticalPosArray = create2Array(R, C);
    horizonPosArray = create2Array(R, C);
    for(var i = 0; i < R; ++i) {
	for(var j = 0; j < C; ++j) {
	    var index = picModel[i][j];
	    var img = document.createElement("img");
	    img.className += "images";
	    if(index != NOPIC)
		img.setAttribute("src", "images/" + imageArray[index]);
	    var id = constructId(i, j);
	    img.setAttribute("id", id);
	    addEvent(img);
	    pics.appendChild(img);
	    verticalPosArray[i][j] = img.offsetLeft;
	    horizonPosArray[i][j] = img.offsetTop;
	}
	var br = document.createElement("br");
	pics.appendChild(br);
    }
}

function create2Array(x, y) {
    var a2 = new Array(x);
    for(i = 0; i < x; ++i)
	a2[i] = new Array(y);
    return a2;
}


function addEvent(img) {
    img.onclick = function() {
	var id = img.getAttribute("id");
	var x = getXFromId(id);
	var y = getYFromId(id);
	if(picModel[x][y] == NOPIC) return;
	if(link_x1 == -1) {
	    link_x1 = x;
	    link_y1 = y;
	    img.className += "clickHere";
//	    fillPic(horizonPosArray[x][y], verticalPosArray[x][y]);
	} else {
	    if(link_x1 == x && link_y1 == y) return;
	    if(picModel[x][y] != picModel[link_x1][link_y1]) {
		var prev = document.getElementById(constructId(link_x1, link_y1));
		prev.className -= "clickHere";
		link_x1 = x;
		link_y1 = y;
		img.className += "clickHere";
		return;
	    }

	    var path = findPath(link_x1, link_y1, x, y);
	    
	    if(path.length != 0) {
		tracePath(path, link_x1, link_y1, x, y);
		var pic1 = document.getElementById(constructId(link_x1, link_y1));
		var pic2 = document.getElementById(constructId(x, y));
		picModel[x][y] = NOPIC;
		picModel[link_x1][link_y1] = NOPIC;
		pic1.setAttribute("src", defaultImg);
		pic2.setAttribute("src", defaultImg);
		link_x1 = -1;
		link_y1 = -1;
	    } else {
		link_x1 = -1;
		link_y1 = -1;
	    }
	}
    }
}

function fillPic(x, y) {
    canvas1 = document.createElement("canvas");
    document.body.appendChild(canvas1);
    var context = canvas1.getContext("2d");
    context.fillRect(x, y, 1000, 500);
}

function tracePath(path, x1, y1, x2, y2) {
    x1 = parseInt(x1) + 1;
    y1 = parseInt(y1) + 1;
    x2 = parseInt(x2) + 1;
    y2 = parseInt(y2) + 1;
    var row = path.length;
    var col = path[0].length;
    console.log("init:");
    console.log("start: (" + x1 + ", " + y1 + ")");
    console.log("end:   (" + x2 + ", " + y2 + ")");
    console.log();
    console.log("path:");
    console.log("(" + x2 + ", " + y2 + ")");
    while(x1 != x2 || y1 != y2) {
	var xy = path[x2][y2];
	x2 = parseInt(xy / row);
	y2 = parseInt(xy % row);
	console.log("(" + x2 + ", " + y2 + ")");
    }
}

function constructId(i, j) {
    return "img" + "_" + i + "_" + j;
}
function getXFromId(id) {
    var i1 = id.indexOf("_");
    var i2 = id.lastIndexOf("_");
    return id.substr(i1 + 1, i2 - i1 - 1);
}
function getYFromId(id) {
    var i = id.lastIndexOf("_");
    return id.substr(i + 1);
}


function prepare() {
    picModel = createModel(nRow, nCol, nPics);
    drawPanel("pictures", nRow, nCol);
}

window.onload = prepare;

