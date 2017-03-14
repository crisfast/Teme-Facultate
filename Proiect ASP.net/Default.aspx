<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
    <div>
        <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="false" class="gridview" ShowHeader="false" GridLines="None">
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
                                            <button class="btn btn-primary btn-xs"><a href='<%# Eval("Id", "Editare.aspx?Id={0}") %>'>Editeaza</a></button>
                                            <button class="btn btn-danger btn-xs"><a href='<%# Eval("Id", "Sterge.aspx?Id={0}") %>' class="link-buton">Sterge</a></button>
                                        </LoggedInTemplate>
                                    </asp:LoginView>

                                    <%--Asta vede editorul--%>
                                    <asp:LoginView ID="LoginView2" runat="server">
                                        <RoleGroups>
                                            <asp:RoleGroup Roles="Administrator,Standard"></asp:RoleGroup>
                                        </RoleGroups>
                                        <LoggedInTemplate>
                                           <button class="btn btn-primary btn-xs"><a href='<%# Eval("Id", "Editare.aspx?Id={0}") %>'>Editeaza</a></button>
                                        </LoggedInTemplate>
                                    </asp:LoginView>
                                </div>
                                <br />
                                <img src="Images/<%#Eval("NumePoza") %>.jpg" width="50%" height="50%" style="display: block; margin: auto;" /> <br />
                                <div id="blbodythumb">
                                    <div class="text-sm-center">
                                        <asp:Label ID="Label100" runat="server" Text='<%# Limit(Eval("Continut"),500) %>' ToolTip='<%# Eval("Continut") %>'>></asp:Label>
                                        <asp:HyperLink ID="ReadMore" runat="server" Visible='<%# SetVisibility(Eval("Continut"), 500) %>' NavigateUrl='<%# Eval("Id", "Articol.aspx?Id={0}") %>'>Citeste mai departe</asp:HyperLink>
                                    </div>
                                </div>
                                <hr class="style-one" />
                            </td>
                        </tr>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
            <EmptyDataTemplate>
                Nu exista postari!
            </EmptyDataTemplate>
        </asp:GridView>
    </div>
</asp:Content>

