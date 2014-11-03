<html>
	<head>
        <meta charset="utf-8">
        <title><VAR:title></title>
        <link rel="stylesheet" href="src/style.css" media="screen">
        <script src="src/raphael.js"></script>
        <script src="src/g.raphael.js"></script>
        <script src="src/g.bar.js"></script>
        <script>
            window.onload = function () {
                var r = Raphael("holder"),
                    data = [<VAR:data1>, <VAR:data2>],
                    txtattr = { font: "18px sans-serif" };
                
                r.text(320, 15, "<VAR:title>").attr(txtattr);
                
                r.barchart(10, 10, 620, 530, data, {stacked: true});
            };
        </script>
    	<style>.pkt_added {text-decoration:none !important;}</style>
    </head>
    <body class="raphael" id="g.raphael.dmitry.baranovskiy.com">
        <div id="holder"></div>
    </body>
</html>