<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Articol.aspx.cs" Inherits="Articol" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
    <div>
        <asp:DetailsView ID="DetailsView1" runat="server" AutoGenerateRows="false" Width="100%" GridLines="None">
            <Fields>
                <asp:TemplateField ShowHeader="false">
                    <ItemTemplate>
                        <tr>
                            <div>
                                <h2>
                                    <asp:Label ID="lblBlogPostTitle" runat="server" Text='<%#Eval("Titlu") %>'></asp:Label></h2>
                                <div>
                                    Scris de
                                    <span>
                                        <a href='<%# Eval("Autor", "Cauta.aspx?Tip=Posts&NumeAutor={0}")%>'>
                                            <asp:Label ID="Label2" runat="server" Text='<%#Eval("Autor") %>'></asp:Label></a></asp:Label>,</span>
                                    legat de 
                                    <span>
                                        <a href='<%# Eval("Categorie", "Cauta.aspx?Tip=Categ&NumeCateg={0}")%>'>
                                            <asp:Label ID="Label3" runat="server" Text='<%#Eval("Categorie") %>'></asp:Label></a>,</span>
                                    pe
                                    <span>
                                        <asp:Label ID="lblBlogDate" runat="server" Text='<%#Eval("DataPost") %>'></asp:Label></span>
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
                                <img src="Images/<%#Eval("NumePoza") %>.jpg" width="50%" height="50%" style="display: block; margin: auto;" /> <br />
                                <div>
                                        <asp:Label ID="lblMessage" runat="server" Text='<%#Eval("Continut") %>'></asp:Label>   
                                </div>
                            </div>
                        </tr>
                    </ItemTemplate>
                </asp:TemplateField>
            </Fields>
        </asp:DetailsView>
    </div>
    <div>
        <hr />
        <h3>Comentarii:</h3>
        <br />
        <asp:GridView ID="GridViewcomment" runat="server" AutoGenerateColumns="false" ShowHeader="false" GridLines="None" Width="100%" CellSpacing="10">
            <Columns>
                <asp:TemplateField>
                    <ItemTemplate>
                        <tr>
                            <td>
                                <div>
                                    <a href='<%# Eval("Nume", "Cauta.aspx?Tip=Comms&NumeUser={0}")%>'>
                                        <asp:Label ID="lblauthor" runat="server" Text='<%#Eval("Nume") %>' Font-Bold="true" Font-Italic="true" Font-Size="Large"></asp:Label></a>
                                    <asp:Label ID="LabelCommentDate" runat="server" Text='<%# Eval("Data") %>' Font-Size="Smaller"></asp:Label>
                                </div>
                                <div>
                                    <p>
                                        <asp:Label ID="Lblcomment" runat="server" Text='<%#Eval("Comentariu") %>'></asp:Label>
                                    </p>
                                </div>
                            </td>
                        </tr>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
        <asp:Label ID="LabelNoComment" runat="server" Text="Nu exista comentarii." Visible="false"></asp:Label>
    </div>
    <div id="comment_form">
        <br />
        <h5>Posteaza un comentariu</h5>
        <asp:LoginView ID="LoginView3" runat="server">
            <LoggedInTemplate><p>Comentezi sub numele de <strong><%= Page.User.Identity.Name  %> </strong></p></LoggedInTemplate>
            <AnonymousTemplate>Te rog sa te <a href="Inregistrare.aspx">inregistrezi</a> sau sa te <a href="Autentificare.aspx">autentifici</a> pentru a putea sa comentezi. <br /><br /></AnonymousTemplate>
        </asp:LoginView>
        
        <asp:TextBox ID="txbxcomment" runat="server" TextMode="MultiLine"></asp:TextBox><br />
        <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="Comentariul nu poate sa fie gol" ControlToValidate="txbxcomment" CssClass="eroare-adaugare"></asp:RequiredFieldValidator><br />
        <asp:Button ID="comSub" runat="server" Text="Trimite" OnClick="Button1_Click"/>
    </div>
</asp:Content>

