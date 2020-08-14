function myFunction2()
{
    const checkbox = document.getElementById("outputCheck");
    const text2 = document.getElementById("textfield-result");
    const form = document.getElementById("mainForm");
    if(checkbox.checked)
    {
        text2.disabled=true;
        form.setAttribute("action","/cypherFile.txt");
    }
    else
    {
        text2.disabled = false;
        form.setAttribute("action","/caesar");
    }
}