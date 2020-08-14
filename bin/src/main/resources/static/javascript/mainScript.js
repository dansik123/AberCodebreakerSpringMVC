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
    if(text1.disabled)
    {
        text1.disabled = false;
        filePicker.disabled = true;
    }
    if(text2.disabled)
    {
        text2.disabled = false;
    }
}