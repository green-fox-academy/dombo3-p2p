console.log("Hello buzikam");

$("#input").keypress(function (e) {
  console.log( "Handler for .keypress() called." );
//    if (e.which == 13) {
//      alert("Bazzeee");
//      $('#sendmessage').submit();
//      event.preventDefault();
//    }
});