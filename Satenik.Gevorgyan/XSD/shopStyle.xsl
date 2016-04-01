<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
  <head>
  	<title>Shop</title>
	<style>
h2{
    font-size: 20px;
    margin: 0;
}

h4{
	font-family: 'Open Sans', sans-serif
    margin: 5;
	color: #01DF01; 
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

img{
    position: relative;    
    width:  150px;
    height: 120px;    
    background-repeat:   no-repeat;

}

table td {
vertical-align: top;
}

.productor{
background-color:  #0B3B0B;
color: #FFBF00; 
font-family: 'Open Sans', sans-serif

font-size: 15px; font-weight: bold; 
 letter-spacing: -1px; 
 line-height: 1; 
 text-align: left; 
}

.department table td {
 letter-spacing: -1px; 
 line-height: 1; 
 text-align: left; 
 width:  150px;
}
.depName {
color: #FFBF00; 
font-family: 'Open Sans',
size: 70px; font-weight: bold; 
 letter-spacing: -1px; 
 line-height: 1; 
 text-align: center; 
  width:  150px;
  valign="baseline"
}


</style>
  </head>
  <body>
    <h2>Products by departments</h2>
	<xsl:for-each select="shop/department">
	    <table class="department" border="0" cellspacing="0">			
			<tr>
				<td><div class="image"><img src="images/{@img}.jpg" />
		</div></td>
				<td class="depName"><h3><xsl:value-of select="@name"/></h3></td>
			</tr>
		</table>
		<h4>Productors</h4>	
			<xsl:for-each select="productors/productor">  
				<table  class="productors" border="2" width="600" cellspacing="0">			
			<tr>
				<td class="productor"><xsl:value-of select="@productorName"/></td>
			</tr>	
			<tr>
			<xsl:for-each select="goods/good">  
				<table border="2" width="600" cellspacing="0">			
			<tr>				
				<td style="width: 150px;"><xsl:value-of select="@goodName"/></td>
				<td>
				<span class = "span1">Producted Date :	</span><span class = "span2"><xsl:value-of select="productDate"/></span><br/>
		<span class = "span1">Expiration Date :	</span><span class = "span2"> <xsl:value-of select="expireDate"/></span><br/>
		<span class = "span1">Size/Measure : 	</span><span class = "span2"><xsl:value-of select="size"/>/<xsl:value-of select="@measurement"/></span><br/>
		<span class = "span11">Price :	</span><span class = "span12"><xsl:value-of select="price"/></span><br/>
				</td>
			</tr>
		</table>
		</xsl:for-each>	
			</tr>
			
		</table>	
	</xsl:for-each>	
		<hr/>
	</xsl:for-each>
	
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>