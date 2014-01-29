function hideShowImage(spanID, imgID) {
  spanElement = document.getElementById(spanID);
  imgElement = document.getElementById(imgID);
  if (imgElement.style.display == "none") {
    imgElement.style.display = "";
    spanElement.innerHTML = "Hide image";
    spanElement.className = "imagehide";
  } else {
    imgElement.style.display = "none";
    spanElement.innerHTML = "Show image";
    spanElement.className = "imageshow";
  }
}

function hideShowElement(spanID, elementID) {
  spanElement = document.getElementById(spanID);
  targetElement = document.getElementById(elementID);
  if (targetElement.style.display == "none") {
    targetElement.style.display = "";
    spanElement.title = "Collapse section";
    spanElement.className = "sectioncollapse";
  } else {
    targetElement.style.display = "none";
    spanElement.title = "Expand section";
    spanElement.className = "sectionexpand";
  }
}

function collapseAll()
{
	var pc = document.getElementById("primary-channel");
	var items = pc.getElementsByTagName("span");
	for( var i = 0; i < items.length; ++i )
	{
		if( items[i].className == "sectioncollapse" )
		{
			var ah = items[i].getElementsByTagName("A")[0].href;
			if( ah.substr(0, 11) == "javascript:" )
			{
				ah = ah.substr(11);
				eval(ah);
			}
		}
	}
}

function expandAll()
{
	var pc = document.getElementById("primary-channel");
	var items = pc.getElementsByTagName("span");
	for( var i = 0; i < items.length; ++i )
	{
		if( items[i].className == "sectionexpand" )
		{
			var ah = items[i].getElementsByTagName("A")[0].href;
			if( ah.substr(0, 11) == "javascript:" )
			{
				ah = ah.substr(11);
				eval(ah);
			}
		}
	}
}