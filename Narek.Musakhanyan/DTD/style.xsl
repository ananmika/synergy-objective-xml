<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
<html>
<head><style>
.emblem span{display: none; list-style: none;} 
.emblem {position:relative; display:inline-block;}
.emblem:hover span{display:block; position:absolute; top:22px; left:330px;}
</style>
</head>
<body>
	<xsl:for-each select="football/team">
		<div class="emblem">
			<h2 class="emblem">
				<xsl:value-of select="@name"/>
			</h2>
			<span><img src="img/{@name}.png"/></span>
		</div>
		
		<div>Stadium: <span style="color:#ff0000">
			<xsl:value-of select="stadium"/></span>
		</div>
		<div>Founded: <span style="color:#088A08">
			<xsl:value-of select="founded"/></span>
		</div>
		<div>Nickname: <span style="color:#04B4AE">
			<xsl:value-of select="nickname"/></span>
		</div>
		<div>League: <span style="color:#0B0B61">
			<xsl:value-of select="league"/></span>
		</div>
		<div>Capacity: <span style="color:#5F4C0B;">
			<xsl:value-of select="capacity"/></span>
		</div>
			
		<table border="1" cellspacing="0" style="margin-top:20px; margin-bottom:50px; width:600px; text-align: center;">
			<tr bgcolor="#58ACFA">
				<th style="padding:10px">Coach</th>
				<td colspan="30"><xsl:value-of select="coach"/></td>
			</tr>
			<tr bgcolor="#04B4AE">
				<th colspan="1" rowspan="5">Members</th>
			</tr>
				<xsl:for-each select="members/member">
					<xsl:choose>
						<xsl:when test="@position = 'goalkeeper'">
							<tr>
								<xsl:for-each select="player">
									<td colspan="30" bgcolor="#F3F781">
										<xsl:value-of select="."/>
									</td>
								</xsl:for-each>
							</tr>
						</xsl:when>
						<xsl:when test="@position = 'defender'">
							<tr>
								<xsl:for-each select="player">
									<td colspan="10" bgcolor="#04B45F">
										<xsl:value-of select="."/>
									</td>
								</xsl:for-each>
							</tr>								
						</xsl:when>
						<xsl:when test="@position = 'midfielder'">
							<tr>
								<xsl:for-each select="player">
									<td colspan="6" bgcolor="#F7BE81">
										<xsl:value-of select="."/>
									</td>
								</xsl:for-each>
							</tr>
						</xsl:when>
						<xsl:when test="@position = 'forward'">
							<tr>
								<xsl:for-each select="player">
									<td colspan="15" bgcolor="#FA5858">
										<xsl:value-of select="."/>
									</td>
								</xsl:for-each>
							</tr>
						</xsl:when>
						<xsl:otherwise>
							<tr>
								<xsl:for-each select="player">
									<td bgcolor="#cccccc">
										<xsl:value-of select="."/>
									</td>
								</xsl:for-each>
							</tr>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:for-each>
		</table>
	</xsl:for-each>
</body>
</html>
</xsl:template>
</xsl:stylesheet>