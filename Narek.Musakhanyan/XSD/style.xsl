<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
<html>
<head><style>
.emblem span{display: none; list-style: none;} 
.emblem {position:relative; display:inline-block; cursor:pointer;}
.emblem:hover span{display:block; position:absolute; top:22px; left:330px;}
</style>
</head>
<body>

	<xsl:for-each select="cars/car">
		<div class="emblem">
			<h2 class="emblem">
				<xsl:value-of select="@company"/>
			</h2>
			<span><img src="Icons/{@company}.jpg"/></span>
		</div>
		
		<div>Founded: <span style="color:#088A08">
			<xsl:value-of select="founded"/></span>
		</div>
		<div>Headquarters: <span style="color:#04B4AE">
			<xsl:value-of select="headquarters"/></span>
		</div>
		<div>Website: <span class="emblem" style="color:#0B0B61">
			<a href="http://{website}" ><xsl:value-of select="website"/></a></span>
		</div>
			
		<table border="1" cellspacing="0" style="margin-top:40px; margin-bottom:20px; width:600px; text-align: center;">
			<tr bgcolor="#32A81">
				<th colspan="1" rowspan="5">Predecessors</th>
			</tr>
				<xsl:for-each select="predecessor">
					<tr>
						<td colspan="4" bgcolor="#04B4AE">
							<xsl:value-of select="@name"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" bgcolor="#C088A0">
							<xsl:value-of select="from"/>
						</td>
						<td colspan="2" bgcolor="#F3CE12">
							<xsl:value-of select="to"/>
						</td>
					</tr>
				</xsl:for-each>
		</table>
		
		<table border="1" cellspacing="0" style="margin-top:20px; margin-bottom:20px; width:600px; text-align: center;">
			<tr bgcolor="#F5D0A9">
				<th colspan="1" rowspan="4">Founders</th>
			</tr>
				<xsl:for-each select="founders/founder">
					<tr bgcolor="#01DFD7">
						<td><xsl:value-of select="."/></td>
					</tr>
				</xsl:for-each>
		</table>
		
		<table border="1" cellspacing="0" style="margin-top:10px; margin-bottom:20px; width:600px; text-align: center;">
			<tr bgcolor="#E0E6F8">
				<th colspan="1" rowspan="6">Products</th>
			</tr>
				<xsl:for-each select="products/product">
					<tr bgcolor="#81F79F">
						<td><xsl:value-of select="."/></td>
					</tr>
				</xsl:for-each>
		</table>
		
		<table border="1" cellspacing="0" style="margin-top:10px; margin-bottom:20px; width:600px; text-align: center; border:1px solid black;">
			<tr bgcolor="#A4A4A4">
				<th colspan="1" rowspan="4">Owners</th>
				<th>Owner</th>
				<th>Percent %</th>
			</tr>
				<xsl:for-each select="owners/owner">
					<tr bgcolor="#FF8000">
						<td>
							<xsl:value-of select="."/>
						</td>
						<td>
							<xsl:value-of select="@percent"/>
						</td>
					</tr>
				</xsl:for-each>
		</table>
	</xsl:for-each>
</body>
</html>
</xsl:template>
</xsl:stylesheet>