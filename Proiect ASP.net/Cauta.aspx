<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Cauta.aspx.cs" Inherits="Cauta" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
    <div>
        <asp:gridview id="GridView1" runat="server" autogeneratecolumns="false" class="gridview" showheader="false" gridlines="None">
            <Columns>
                <asp:TemplateField ShowHeader="false">
                    <ItemTemplate>
                        <tr>
                            <td>
                                <div class="BlogHead">
                                    <h2>
                                        <a href='<%# Eval("Id", "Articol.aspx?Id={0}") %>' class="BlogHead">
                                            <asp:Label ID="Label1" runat="server" Text='<%#Eval("Titlu") %>'></asp:Label></a>
                                    </h2>
                                </div>
                                <div class="post_meta">
                                    Scris de
                                    <span class="post_author blackLink nocursor">
                                        <a href='<%# Eval("Autor", "Cauta.aspx?Tip=Posts&NumeAutor={0}")%>'>
                                            <asp:Label ID="Label2" runat="server" Text='<%#Eval("Autor") %>'></asp:Label></a>,</span>
                                    legat de
                                    <span class="post_author blackLink nocursor">
                                        <a href='<%# Eval("Categorie", "Cauta.aspx?Tip=Categ&NumeCateg={0}")%>'>
                                            <asp:Label ID="Label3" runat="server" Text='<%#Eval("Categorie") %>'></asp:Label></a>,</span>
                                    pe
                                    <span class="date blackLink nocursor">
                                        <asp:Label ID="Label11" runat="server" Text='<%#Eval("DataPost") %>'></asp:Label></span>
                                    <%--Asta vede doar adminul--%>
                                    <asp:LoginView ID="LoginView1" runat="server">
                                        <RoleGroups>
                                            <asp:RoleGroup Roles="Editor,Standard"></asp:RoleGroup>
                                        </RoleGroups>
                                        <LoggedInTemplate>
                                            <a href='<%# Eval("Id", "Editare.aspx?Id={0}") %>'>Editeaza</a>
                                            <a href='<%# Eval("Id", "Sterge.aspx?Id={0}") %>'>Sterge</a>
                                        </LoggedInTemplate>
                                    </asp:LoginView>

                                    <%--Asta vede editorul--%>
                                    <asp:LoginView ID="LoginView2" runat="server">
                                        <RoleGroups>
                                            <asp:RoleGroup Roles="Administrator,Standard"></asp:RoleGroup>
                                        </RoleGroups>
                                        <LoggedInTemplate>
                                            <a href='<%# Eval("Id", "Editare.aspx?Id={0}") %>'>Editeaza</a>
                                        </LoggedInTemplate>
                                    </asp:LoginView>
                                </div>
                                <br />
                                <img src="Images/<%#Eval("NumePoza") %>.jpg" width="25%" height="25%" style="display: block; margin: auto;" />
                                <div id="blbodythumb" style="text-align: justify;">
                                   
                                        <asp:Label ID="Label100" runat="server" Text='<%#Eval("Continut") %>'></asp:Label>
                                    
                                </div>
                                <hr class="style-one" />
                            </td>
                        </tr>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
            <EmptyDataTemplate>
                Nu s-au gasit postari!
            </EmptyDataTemplate>
        </asp:gridview>
    </div>

    <div id="arataComms">
    <asp:gridview id="GridViewcomment" runat="server" autogeneratecolumns="false" showheader="false" gridlines="None" width="100%" cellspacing="10">
             <Columns>
                <asp:TemplateField>
                    <ItemTemplate>
                        <tr>
                            <td >
                                <div style="padding-bottom: 10px;">
                                    <asp:Label ID="lblauthor" runat="server" Text='<%#Eval("Nume") %>' Font-Bold="true" Font-Italic="true" Font-Size="Large"></asp:Label>
                                    <asp:Label ID="LabelCommentDate" runat="server" Text='<%# Eval("Data") %>' Font-Size="Smaller"></asp:Label>
                                </div>
                                <div>
                                    <p  style="background-color: #efefef">
                                        <asp:Label ID="Lblcomment" runat="server" Text='<%#Eval("Comentariu") %>'></asp:Label> 
                                    </p>
                                </div>
                                <hr />
                            </td>
                        </tr>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:gridview>
         <asp:Label ID="LabelNoComment" runat="server" Text="Nu exista comentarii." Visible="false"></asp:Label>
    </div>
</asp:Content>

