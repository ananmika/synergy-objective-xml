<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
  <head>
  	<title>Recipes Book</title>
  </head>
  <body>
    <h2>Recipes</h2>
	<h3>Main Dishes</h3>
	<xsl:for-each select="recipes/recipe[@category='main dish']">
	    <table border="2" width="600" cellspacing="0">
			<tr bgcolor="#9acd32">
				<th>Name</th>
				<th>Country of Origin</th>
				<th>Picture</th>
			</tr>
			<tr>
				<td><xsl:value-of select="name"/></td>
				<td><xsl:value-of select="countryOfOrigin"/></td>
				<td><img src="{name/@imgSource}"/></td>
			</tr>
		</table>
		<h4>Ingredients</h4>			 
		<ul>
			<xsl:for-each select="ingredients/ingredient">  
					<li><xsl:value-of select="name"/> - <xsl:value-of select="quantity"/> <xsl:value-of select="measurementUnit"/><xsl:if test="@state">, <xsl:value-of select="@state"/></xsl:if></li>
			</xsl:for-each>
		</ul> 
		<h4>Cooking Process</h4>
		<ol>
			<xsl:for-each select="cooking/steps/step">   
				<li><xsl:value-of select="."/></li>
			</xsl:for-each>
		</ol>
		<hr/>
	</xsl:for-each>
	<h3>Desserts</h3>
	<xsl:for-each select="recipes/recipe[@category='dessert']">
	    <table border="2" width="600" cellspacing="0">
			<tr bgcolor="#9acd32">
				<th>Name</th>
				<th>Country of Origin</th>
				<th>Picture</th>
			</tr>
			<tr>
				<td><xsl:value-of select="name"/></td>
				<td><xsl:value-of select="countryOfOrigin"/></td>
				<td><img src="{name/@imgSource}"/></td>
			</tr>
		</table>
		<h4>Ingredients</h4>			 
		<ul>
			<xsl:for-each select="ingredients/ingredient">  
					<li><xsl:value-of select="name"/> - <xsl:value-of select="quantity"/> <xsl:value-of select="measurementUnit"/><xsl:if test="@state">, <xsl:value-of select="@state"/></xsl:if></li>
			</xsl:for-each>
		</ul> 
		<h4>Cooking Process</h4>
		<ol>
			<xsl:for-each select="cooking/steps/step">   
				<li><xsl:value-of select="."/></li>
			</xsl:for-each>
		</ol>
	</xsl:for-each>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>