

function customAlert(p_msg){
	alert(p_msg);
}


function customConfirm(p_msg){
	return confirm(p_msg);
}

function nullToStrReplace(str, replaceStr){
	if(str==null || typeof str =="undefined" || str==""){
		return replaceStr;
	}else{
		return str;
	}
}


function replaceAll(str, orgStr, repStr){
    return str.split(orgStr).join(repStr);
}


//콤마 제거
function removeComma(n){ //제거
	n=new String(n); 
	return parseInt(n.replace(/,/gi,""));
}
//3자리 마다 콤마 찍기 
function addComma(num){		
	if(num=="")	num = 0;
	if(isNaN(num))  num = 0;	
    if(num == 0) return num;
    
	num = removeComma(num);
	
	var sign="";	
    if(num < 0){ 
        num = num * (-1); 
        sign="-"; 
    }else{ 
        num=num*1; 
    }
       
    num = new String(num) 
    var temp=""; 
    var pos=3;     
    num_len=num.length; 
    
    while (num_len>0){ 
        num_len=num_len-pos; 
        if(num_len<0) { 
            pos=num_len+pos; 
            num_len=0; 
        } 
        temp=","+num.substr(num_len,pos)+temp; 
    } 

	num = sign+temp.substr(1);	
	return num;	
} 
