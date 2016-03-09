<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
  <head>
	<style>
		.name span	{
						display: none;
						border: collapse;
					}
		.name 	{
						position:relative;
				}
		.name:hover span	{
								display:block;
								position:absolute;
								margin-left: 1215px;
							}
	</style>  
  </head>
  <body>
    <h2>Animals in our Zoo</h2>
    <table border="2" width="1200" cellspacing="0">
		<tr bgcolor="#9acd32">
			<th rowspan="2">Name</th>
			<th rowspan="2">Scientific Name</th>
			<th colspan="2">Breeding</th>
			<th colspan="2">Average Weight(kg)</th>
			<th rowspan="2">Description</th>
			<th rowspan="2">Endangerment Status</th>
		</tr>
		<tr bgcolor="#9acd32">
			<th>Quantity</th>
			<th>Duration</th>
			<th>Male</th>
			<th>Female</th>
		</tr>
		<xsl:for-each select="animals/animal">
			<xsl:choose>
				<xsl:when test="@category = 'mammal'">
					<tr bgcolor="red">
						<td class="name">
							<span>
								<img src="Resources\Images\{name}.jpg"/>
							</span>
							<xsl:value-of select="name"/>
						</td>
						<td><xsl:value-of select="scname"/></td>
						<td><xsl:value-of select="breeding/quantity"/></td>
						<td><xsl:value-of select="breeding/period"/></td>
						<td><xsl:value-of select="avgWeight/male"/></td>
						<td><xsl:value-of select="avgWeight/female"/></td>
						<td><img src="Resources\Images\{description/@diet}.png" alt="{description/@diet}"/><xsl:value-of select="description"/></td>
						<td><xsl:value-of select="dngStatus"/></td>
					</tr>
				</xsl:when>
				<xsl:when test="@category = 'bird'">
					<tr bgcolor="green">
						<td class="name"><span><img src="Resources\Images\{name}.jpg"/></span><xsl:value-of select="name"/></td>
						<td><xsl:value-of select="scname"/></td>
						<td><xsl:value-of select="breeding/quantity"/></td>
						<td><xsl:value-of select="breeding/period"/></td>
						<td><xsl:value-of select="avgWeight/male"/></td>
						<td><xsl:value-of select="avgWeight/female"/></td>
						<td><img src="Resources\Images\{description/@diet}.png" alt="{description/@diet}"/><xsl:value-of select="description"/></td>
						<td><xsl:value-of select="dngStatus"/></td>
					</tr>
				</xsl:when>
				<xsl:when test="@category = 'fish'">
					<tr bgcolor="blue">
						<td class="name"><span><img src="Resources\Images\{name}.jpg"/></span><xsl:value-of select="name"/></td>
						<td><xsl:value-of select="scname"/></td>
						<td><xsl:value-of select="breeding/quantity"/></td>
						<td><xsl:value-of select="breeding/period"/></td>
						<td><xsl:value-of select="avgWeight/male"/></td>
						<td><xsl:value-of select="avgWeight/female"/></td>
						<td><img src="Resources\Images\{description/@diet}.png" alt="{description/@diet}"/><xsl:value-of select="description"/></td>
						<td><xsl:value-of select="dngStatus"/></td>
					</tr>
				</xsl:when>
				<xsl:when test="@category = 'invertebrate'">
					<tr bgcolor="pink">
						<td class="name"><span><img src="Resources\Images\{name}.jpg"/></span><xsl:value-of select="name"/></td>
						<td><xsl:value-of select="scname"/></td>
						<td><xsl:value-of select="breeding/quantity"/></td>
						<td><xsl:value-of select="breeding/period"/></td>
						<td><xsl:value-of select="avgWeight/male"/></td>
						<td><xsl:value-of select="avgWeight/female"/></td>
						<td><img src="Resources\Images\{description/@diet}.png" alt="{description/@diet}"/><xsl:value-of select="description"/></td>
						<td><xsl:value-of select="dngStatus"/></td>
					</tr>
				</xsl:when>
				<xsl:when test="@category = 'amphibian'">
					<tr bgcolor="yellow">
						<td class="name"><span><img src="Resources\Images\{name}.jpg"/></span><xsl:value-of select="name"/></td>
						<td><xsl:value-of select="scname"/></td>
						<td><xsl:value-of select="breeding/quantity"/></td>
						<td><xsl:value-of select="breeding/period"/></td>
						<td><xsl:value-of select="avgWeight/male"/></td>
						<td><xsl:value-of select="avgWeight/female"/></td>
						<td><img src="Resources\Images\{description/@diet}.png" alt="{description/@diet}"/><xsl:value-of select="description"/></td>
						<td><xsl:value-of select="dngStatus"/></td>
					</tr>
				</xsl:when>
				<xsl:otherwise>
					<tr bgcolor="brown">
						<td class="name"><span><img src="Resources\Images\{name}.jpg"/></span><xsl:value-of select="name"/></td>
						<td><xsl:value-of select="scname"/></td>
						<td><xsl:value-of select="breeding/quantity"/></td>
						<td><xsl:value-of select="breeding/period"/></td>
						<td><xsl:value-of select="avgWeight/male"/></td>
						<td><xsl:value-of select="avgWeight/female"/></td>
						<td><img src="Resources\Images\{description/@diet}.png" alt="{description/@diet}"/><xsl:value-of select="description"/></td>
						<td><xsl:value-of select="dngStatus"/></td>
					</tr>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:for-each>
    </table>
		<ol>
			<li>Mammals - Red</li>
			<li>Birds - Green</li>
			<li>Invertebrates - Pink</li>
			<li>Amphibians - Yellow</li>
			<li>Fishes - Blue</li>
			<li>Reptiles - Brown</li>
		</ol>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>