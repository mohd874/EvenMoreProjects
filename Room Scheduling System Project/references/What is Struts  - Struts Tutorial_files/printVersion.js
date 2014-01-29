_win = new Object();

function printVersion(strID) {
	var begin = '';
	var end = '';
	var ind1 = -1;
	var ind2 = -1;
	var innerHTML = '';
	var headerHTML = '';

	if ( '' == strID || undefined == strID )
		return false;

	// common HTML
	begin = '<!--[' + strID  + ']//-->';
	end = '<!--[!' + strID  + ']//-->';

	ind1 = document.body.innerHTML.indexOf(begin);
	ind2 = document.body.innerHTML.indexOf(end);

	if ( -1 == ind1 || -1 == ind2 )
		return false;

	innerHTML = document.body.innerHTML.substring(ind1 + begin.length, ind2);

	// header HTML
	begin = '<!--[h_' + strID  + ']';
	end = '[!h_' + strID  + ']//-->';

	ind1 = document.body.innerHTML.indexOf(begin);
	ind2 = document.body.innerHTML.indexOf(end);

	if ( -1 == ind1 || -1 == ind2 ) {
		headerHTML = '';
	} else {
		headerHTML = document.body.innerHTML.substring(ind1 + begin.length, ind2);
	}

	if ( null != _win[strID] && undefined != _win[strID] && 'object' == typeof(_win[strID]) && !_win[strID].closed ) {
		_win[strID].focus();
		return true;
	} else {
		_win[strID] = window.open('void.html', '', 'resizable=yes,scrollbars=yes');
	}

	_win[strID]._innerHTML = innerHTML;
	_win[strID]._headerHTML = headerHTML;

	return true;
}