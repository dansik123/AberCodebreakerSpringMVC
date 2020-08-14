function myFunction()
{
    const checkbox = document.getElementById("myCheck");
    const form = document.getElementById("mainForm");
    const text1 = document.getElementById("textfield-text");
    const filePicker = document.getElementById("filePicker");
    if(checkbox.checked)
    {
        form.setAttribute("enctype","multipart/form-data");
        text1.disabled=true;
        filePicker.disabled=false;
    }
    else
    {
        form.removeAttribute("enctype")
        text1.disabled=false;
        filePicker.disabled=true;
    }
}

function onlyUpperCase(textarea) {
    textarea.value = textarea.value.toUpperCase();
}

function backToNormal()
{
    const text1 = document.getElementById("textfield-text");
    const filePicker = document.getElementById("filePicker");
    const text2 = document.getElementById("textfield-result");
    const key = document.getElementById("key");
    const shift = document.getElementById("shift");
    const inputCheck = document.getElementById("myCheck");
    const outputCheck = document.getElementById("outputCheck");
    const decode = document.getElementById("decode");
    
    if(text1.disabled)
    {
        text1.disabled = false;
        filePicker.disabled = true;
    }
    if(text2.disabled)
    {
        text2.disabled = false;
    }
    
    text1.value = "";
    text2.value = "";
    
    if(typeof(key.value)==="string")
    {
    	key.value = "";
    }
    else
    {
    	key.value = 0;
    }
    
    if(typeof(shift) != 'undefined' && shift != null)
    {
    	shift.value = 0;
    }
    if(inputCheck.checked)
    {
    	inputCheck.checked = false;
    }
    if(outputCheck.checked)
    {
    	outputCheck.checked = false;
    }
    
    if(!decode.checked)
    {
    	decode.checked = true;
    }
    
      
}