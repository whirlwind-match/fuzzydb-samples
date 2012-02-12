	<script type="text/javascript">
		function success(position) {
		  var s = document.querySelector('#postcode');
		  s.value = "{" + position.coords.latitude +"," + position.coords.longitude + "}";
// 			alert( "you're at: " + position.coords.latitude +"," + position.coords.longitude );
		};

		function error(msg) {
// 	 			alert( 'geolocation not supported: ' + msg );
		};

		function getLocation() {
			if (navigator.geolocation) {
			  navigator.geolocation.getCurrentPosition(success, error);
			} else {
			  error('not supported');
			}
		};

		$(document).bind('mobileinit',function(){
		   $.mobile.selectmenu.prototype.options.nativeMenu = false;
		   $.mobile.ajaxEnabled = false;
			$( '#search-page' ).live( 'pagecreate',function(event){
				getLocation();
			});
		});
	</script>
	