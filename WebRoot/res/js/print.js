function doPrint() {   
    bdhtml = window.document.body.innerHTML;
    sprnstr = "<!--startprint-->";
    eprnstr = "<!--endprint-->";
    prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
    prnhtml = prnhtml.substring(0,prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML = prnhtml;
    window.print();
}