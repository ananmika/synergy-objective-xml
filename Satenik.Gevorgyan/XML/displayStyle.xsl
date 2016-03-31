<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" /> 

<xsl:template match="/"> 
<link rel="stylesheet" type="text/css" href="dxsl.css"/> 
<html>
<head>
<style>
h2{
    font-size: 20px;
    margin: 0;
}

.span1{
color: #1C1C1C
font-family: 'Open Sans', sans-serif
 font-size: 15px; font-weight: bold; 
 letter-spacing: -1px; 
 line-height: 1; 
 text-align: center; 
}

.span2{
color: #07190B; 
font-family: 'Open Sans', sans-serif
 font-size: 10px;
 letter-spacing: -1px; 
 line-height: 1; 
 text-align: center; 
}

.span11{
color: green 
}

.span12{
color: red; 
}

.category{
color: #0A0A2A
	width: 300px;
    padding: 10px;    
    margin: 0;
}

.prefix{	
color: #D0FA58
		background-position: left top	
}

.image{	
		background-position: left top	
}

img{
    position: relative;    
    width:  100px;
    height: 100px;    
    background-repeat:   no-repeat;

}

table td {
vertical-align: top;
}
</style>
</head>
<body>
<h2>YOU CAN FIND BEST BOOKS IN OUT SITE</h2>

	<xsl:for-each select="libraryBooks/category">
		<div class="category">
			<h2>
				<span class="prefix">Category: </span><xsl:value-of select="@title"/>
			</h2>
		</div>
		
		<xsl:for-each select="books/book">	
		<table border="0">      
		<tr>
		<td>
		<div class="image"><img src="images/{@img}.jpg" />
		</div>
		</td>	
		<td>
		<span class = "span1">ISBN :	</span><span class = "span2"><xsl:value-of select="isbn"/></span><br/>
		<span class = "span1">Title :	</span><span class = "span2"> <xsl:value-of select="title"/></span><br/>
		<span class = "span1">SubTitle : 	</span><span class = "span2"><xsl:value-of select="subTitle"/></span><br/>
		<span class = "span1">Price :	</span><span class = "span2">xsl:value-of select="price"/></span><br/>
		<span class = "span1">Publisher :	</span><span class = "span2"><xsl:value-of select="publisherHouse"/></span><br/>
		<span class = "span1">Pulished Year :	</span><span class = "span2"><xsl:value-of select="publishingYear"/></span><br/>
		<span class = "span1">Published place :	</span><span class = "span2"><xsl:value-of select="printedPlace"/></span><br/>
		<span class = "span1">Illustrator :	</span><span class = "span2"> <xsl:value-of select="illustrator"/></span><br/>
		<span class = "span11">Remainig Quantity :	</span><span class = "span2"><xsl:value-of select="ramainingQuantity"/></span><br/>
		<span class = "span12">Sold :	</span><span class = "span2"><xsl:value-of select="sold"/></span>	<br/>
		</td>		
		</tr>
				
    </table>
	</xsl:for-each>	
	<br/><br/>

	
	</xsl:for-each>
</body>
</html>
</xsl:template>
</xsl:stylesheet>