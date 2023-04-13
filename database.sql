/*
CREATE TABLE `Devices` (
 `id` int(11) NOT NULL UNIQUE,
  `device_name` varchar(30) UNIQUE NOT NULL,
  `vendor` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `os_version` varchar(30) NOT NULL,
  `owner` varchar(30) NOT NULL,
  `function` varchar(30) NOT NULL,
  `model` varchar(30) NOT NULL,
  `location` varchar(30) NOT NULL);

CREATE TABLE `Os_Version` (
 `id` int(11) NOT NULL UNIQUE,
  `version_name` varchar(30) UNIQUE NOT NULL,
  `devices` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL);

  CREATE TABLE `Function` (
 `id` int(11) NOT NULL UNIQUE,
  `function_name` varchar(30) UNIQUE NOT NULL,
  `description` varchar(30) NOT NULL);

  CREATE TABLE `Location` (
 `id` int(11) NOT NULL UNIQUE,
  `location_name` varchar(30) UNIQUE NOT NULL,
  `building` varchar(30) NOT NULL,
  `room` varchar(30) NOT NULL);

CREATE TABLE `Model` (
 `id` int(11) NOT NULL UNIQUE,
  `model_name` varchar(30) UNIQUE NOT NULL,
  `device_type` varchar(30) NOT NULL,
  `os_last_supported` varchar(30) NOT NULL);

  CREATE TABLE `Vendor` (
 `id` int(11) NOT NULL UNIQUE,
  `vendor_name` varchar(30) UNIQUE NOT NULL,
  `devices` varchar(30) NOT NULL,
  `contact_user` varchar(30) NOT NULL,
  `warrenty_dates` varchar(30) NOT NULL);

 CREATE TABLE `Device_Type` (
 `id` int(11) NOT NULL UNIQUE,
  `device_type` varchar(30) UNIQUE NOT NULL);

CREATE TABLE `Users` (
 `id` int(11) NOT NULL UNIQUE,
  `user_name` varchar(30) UNIQUE NOT NULL,
  `role` varchar(30) NOT NULL,
  `contact` varchar(30) NOT NULL);

  CREATE TABLE `Audit` (
 `id` int(11) NOT NULL UNIQUE,
  `device_name` varchar(30) UNIQUE NOT NULL,
  `type_questions` varchar(30) NOT NULL,
  `score` int(10) NOT NULL,
  `timestamp` varchar(10) NOT NULL);

  CREATE TABLE `Compliance` (
 `id` int(11) NOT NULL UNIQUE,
  `user_name` varchar(30) UNIQUE NOT NULL,
  `score` int(10) NOT NULL,
  `timestamp` varchar(10) NOT NULL);

  CREATE TABLE `Router_Questions` (
 `id` int(11) NOT NULL UNIQUE,
  `question` varchar(60) NOT NULL);

   CREATE TABLE `Switch_Questions` (
 `id` int(11) NOT NULL UNIQUE,
  `question` varchar(60) NOT NULL);

   CREATE TABLE `AP_Questions` (
 `id` int(11) NOT NULL UNIQUE,
  `question` varchar(60) NOT NULL);

   CREATE TABLE `ASA_Questions` (
 `id` int(11) NOT NULL UNIQUE,
  `question` varchar(60) NOT NULL);
  
    CREATE TABLE `Hub_Questions` (
 `id` int(11) NOT NULL UNIQUE,
  `question` varchar(60) NOT NULL);
  
    CREATE TABLE `Bridge_Questions` (
 `id` int(11) NOT NULL UNIQUE,
  `question` varchar(60) NOT NULL);
  
    CREATE TABLE `Server_Questions` (
 `id` int(11) NOT NULL UNIQUE,
  `question` varchar(60) NOT NULL);
  
    CREATE TABLE `Modem_Questions` (
 `id` int(11) NOT NULL UNIQUE,
  `question` varchar(60) NOT NULL);
  
    CREATE TABLE `Compliance_Questions` (
 `id` int(11) NOT NULL UNIQUE,
  `question` varchar(60) NOT NULL);

  CREATE TABLE `Router_Exploits` (
 `id` int(11) NOT NULL UNIQUE,
  `exploit_name` varchar(60) NOT NULL,
  `fix` varchar(60) NOT NULL);

    CREATE TABLE `Switch_Exploits` (
 `id` int(11) NOT NULL UNIQUE,
  `exploit_name` varchar(60) NOT NULL,
  `fix` varchar(60) NOT NULL);

    CREATE TABLE `ASA_Exploits` (
 `id` int(11) NOT NULL UNIQUE,
  `exploit_name` varchar(60) NOT NULL,
  `fix` varchar(60) NOT NULL);

    CREATE TABLE `AP_Exploits` (
 `id` int(11) NOT NULL UNIQUE,
  `exploit_name` varchar(60) NOT NULL,
  `fix` varchar(60) NOT NULL);

    CREATE TABLE `Bridge_Exploits` (
 `id` int(11) NOT NULL UNIQUE,
  `exploit_name` varchar(60) NOT NULL,
  `fix` varchar(60) NOT NULL);

    CREATE TABLE `Server_Exploits` (
 `id` int(11) NOT NULL UNIQUE,
  `exploit_name` varchar(60) NOT NULL,
  `fix` varchar(60) NOT NULL);

    CREATE TABLE `Modem_Exploits` (
 `id` int(11) NOT NULL UNIQUE,
  `exploit_name` varchar(60) NOT NULL,
  `fix` varchar(60) NOT NULL);

    CREATE TABLE `Hub_Exploits` (
 `id` int(11) NOT NULL UNIQUE,
  `exploit_name` varchar(60) NOT NULL,
  `fix` varchar(60) NOT NULL);

  INSERT INTO `Device_Type` (`id`, `device_type`) VALUES
(1,'Switch');

INSERT INTO `Device_Type` (`id`, `device_type`) VALUES
(2,'Router');

INSERT INTO `Device_Type` (`id`, `device_type`) VALUES
(3,'Access_Point');

INSERT INTO `Device_Type` (`id`, `device_type`) VALUES
(4,'Bridge');

INSERT INTO `Device_Type` (`id`, `device_type`) VALUES
(5,'Server');

INSERT INTO `Device_Type` (`id`, `device_type`) VALUES
(6,'Modem');

INSERT INTO `Device_Type` (`id`, `device_type`) VALUES
(7,'Hub');

INSERT INTO `Device_Type` (`id`, `device_type`) VALUES
(8,'ASA');

//Router Questions

INSERT INTO `Router_Questions` (`id`, `question`) VALUES
(1,'Does this router have a firewall?');

INSERT INTO `Router_Questions` (`id`, `question`) VALUES
(2,'Does this router have passwords that are encrypted?');

 INSERT INTO `Router_Questions` (`id`, `question`) VALUES
(3,'Does this device have access to outside network or internet?');

INSERT INTO `Router_Questions` (`id`, `question`) VALUES
(4,'Is the hardware less than 3 years old?');

 INSERT INTO `Router_Questions` (`id`, `question`) VALUES
(5,'Is the router in a secure locked room only accessible to authorised personnel?');

 INSERT INTO `Router_Questions` (`id`, `question`) VALUES
(6,'Does the router have a backup power supply or backup diesel generator?');

 INSERT INTO `Router_Questions` (`id`, `question`) VALUES
(7,'Is the operating system less than 3 months old?');

 INSERT INTO `Router_Questions` (`id`, `question`) VALUES
(8,'Are unused interfaces shutdown?');

 INSERT INTO `Router_Questions` (`id`, `question`) VALUES
(9,'Have unnecessary services been disabled? (eg. http/https)');

 INSERT INTO `Router_Questions` (`id`, `question`) VALUES
(10,'Is there backups of the router operating systems images/config?');

 INSERT INTO `Router_Questions` (`id`, `question`) VALUES
(11,'Does the operating system version meet the feature specifications of the router?');

//Switch Questions

INSERT INTO `Switch_Questions` (`id`, `question`) VALUES
(1,'Does the switch have the accounts encrypted?');

INSERT INTO `Switch_Questions` (`id`, `question`) VALUES
(2,'Are the unused switch ports shutdown?');

 INSERT INTO `Switch_Questions` (`id`, `question`) VALUES
(3,'Is port security enabled on this switch?');

 INSERT INTO `Switch_Questions` (`id`, `question`) VALUES
(4,'Is DHCP snooping enabled ont this switch?');

 INSERT INTO `Switch_Questions` (`id`, `question`) VALUES
(5,'Is Dynamic Arp Inspection enabled on the switch?');

 INSERT INTO `Switch_Questions` (`id`, `question`) VALUES
(6,'Are STP management strategies enabled on the switch?');

 INSERT INTO `Switch_Questions` (`id`, `question`) VALUES
(7,'Is AAA enabled on the switch?');

 INSERT INTO `Switch_Questions` (`id`, `question`) VALUES
(8,'Is the operating system less than 3 months old?');

 INSERT INTO `Switch_Questions` (`id`, `question`) VALUES
(9,'Is the switche hardware less than 3 years old?');

 INSERT INTO `Switch_Questions` (`id`, `question`) VALUES
(10,'Has Dynamic Trunking Protocol been disabled?');

 INSERT INTO `Switch_Questions` (`id`, `question`) VALUES
(11,'Has Link Local Discovery protocol been diabled?');

 INSERT INTO `Switch_Questions` (`id`, `question`) VALUES
(12,'If VLANs are implemented, is management moved from default VLAN 1?');

//ASA Questions 

INSERT INTO `ASA_Questions` (`id`, `question`) VALUES
(1,'Does this device have a firewall configured?');

INSERT INTO `ASA_Questions` (`id`, `question`) VALUES
(2,'Does this device have a VPN configured?');

INSERT INTO `ASA_Questions` (`id`, `question`) VALUES
(3,'Are accounts on this device encrypted?');

INSERT INTO `ASA_Questions` (`id`, `question`) VALUES
(4,'Is the operating system less than 3 months old?');

INSERT INTO `ASA_Questions` (`id`, `question`) VALUES
(5,'Is the device hardware less than 3 years old?');

//Access Point Questions

INSERT INTO `AP_Questions` (`id`, `question`) VALUES
(1,'Does this AP require you to access it with a username and password?');

INSERT INTO `AP_Questions` (`id`, `question`) VALUES
(2,'Is the password complex and uses multiple numbers/letters/special characters?');

INSERT INTO `AP_Questions` (`id`, `question`) VALUES
(3,'Does this AP require you to access it with a username and password?');

INSERT INTO `AP_Questions` (`id`, `question`) VALUES
(4,'Is the traffic filtered on the access point?');

INSERT INTO `AP_Questions` (`id`, `question`) VALUES
(5,'Is the operating system less than 3 months old?');

INSERT INTO `AP_Questions` (`id`, `question`) VALUES
(6,'Is the hardware less than 3 years old?');

INSERT INTO `AP_Questions` (`id`, `question`) VALUES
(7,'Can BYODs connect to this access point?');

//Hub Questions

INSERT INTO `Hub_Questions` (`id`, `question`) VALUES
(1,'Is the operating system less than 3 months old?');

INSERT INTO `Hub_Questions` (`id`, `question`) VALUES
(2,'Is the hardware less than 3 years old?');

INSERT INTO `Hub_Questions` (`id`, `question`) VALUES
(3,'Does the hub have open ports where devices can be plugged into it?');

INSERT INTO `Hub_Questions` (`id`, `question`) VALUES
(4,'Is the hub stored in a room where only authorised personnel can access it?');

INSERT INTO `Hub_Questions` (`id`, `question`) VALUES
(5,'Does this hub use any secuirty features like WPA?');

INSERT INTO `Hub_Questions` (`id`, `question`) VALUES
(6,'Can a switch be implemented instead of a hub?');

//Bridge Questions

INSERT INTO `Bridge_Questions` (`id`, `question`) VALUES
(1,'Is the operating system less than 3 months old?');

INSERT INTO `Bridge_Questions` (`id`, `question`) VALUES
(2,'Is the hardware less than 3 years old?');

INSERT INTO `Bridge_Questions` (`id`, `question`) VALUES
(3,'Is the bridge been used to connect less than 4 networks?');

INSERT INTO `Bridge_Questions` (`id`, `question`) VALUES
(4,'Can a hub be used instead of this bridge device?');

INSERT INTO `Bridge_Questions` (`id`, `question`) VALUES
(5,'Can a hub be used instead of this bridge device?');

INSERT INTO `Bridge_Questions` (`id`, `question`) VALUES
(6,'Is there encryption or authentication enabled on this device?');

//Server Questions

INSERT INTO `Server_Questions` (`id`, `question`) VALUES
(1,'Is the operating system less than 3 months old?');

INSERT INTO `Server_Questions` (`id`, `question`) VALUES
(2,'Is the hardware less than 5 years old?');

INSERT INTO `Server_Questions` (`id`, `question`) VALUES
(3,'Is the server in a safe location where only authorised personnel can access?');

INSERT INTO `Server_Questions` (`id`, `question`) VALUES
(4,'Does the server have sensitive data on it?');

INSERT INTO `Server_Questions` (`id`, `question`) VALUES
(5,'Is there any secuity protocols/features implemented on the server?');

INSERT INTO `Server_Questions` (`id`, `question`) VALUES
(6,'Is their any backup of this server should an attack happen if data is lost?');

INSERT INTO `Server_Questions` (`id`, `question`) VALUES
(7,'Can only Admins and IT technicians access this server?');

//Modem Questions

INSERT INTO `Modem_Questions` (`id`, `question`) VALUES
(1,'Is the operating system less than 3 months old?');

INSERT INTO `Modem_Questions` (`id`, `question`) VALUES
(2,'Is there password protection on this modem?');

INSERT INTO `Modem_Questions` (`id`, `question`) VALUES
(3,'Is the hardware less than 4 years old?');

INSERT INTO `Modem_Questions` (`id`, `question`) VALUES
(4,'Can only authorised users connect to this modem?');

INSERT INTO `Modem_Questions` (`id`, `question`) VALUES
(5,'Is this modem in a safe location where only authorised personnel can access?');

INSERT INTO `Modem_Questions` (`id`, `question`) VALUES
(6,'Should sensitive data be sent through this modem?');

INSERT INTO `Modem_Questions` (`id`, `question`) VALUES
(7,'Does this modem use any secuirty features like WPA?');


//Compliance Questions

INSERT INTO `Compliance_Questions` (`id`, `question`) VALUES
(1,'Is this user allowed to change or edit device configurations?');

INSERT INTO `Compliance_Questions` (`id`, `question`) VALUES
(2,'Is this user an IT technician?');

INSERT INTO `Compliance_Questions` (`id`, `question`) VALUES
(3,'Does the user use complex passwords for their accounts?');

INSERT INTO `Compliance_Questions` (`id`, `question`) VALUES
(4,'Does this user follow secuity policies and guidelines for devices?');

INSERT INTO `Compliance_Questions` (`id`, `question`) VALUES
(5,'Should this user have access to sensitive data?');

INSERT INTO `Compliance_Questions` (`id`, `question`) VALUES
(6,'Is this user allowed to access less than 5 devices/accounts?');

INSERT INTO `Compliance_Questions` (`id`, `question`) VALUES
(7,'Is this user allowed to access server rooms or rooms with many devices?');


//Router exploits

INSERT INTO `Router_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(1,
'Denial Of Service: Flooding the device with traffic,causing it to crash the device and make it inaccessible.',
'Implement Firewall, Use a VPN service, Ensure device is up to date.');

INSERT INTO `Router_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(2,
'Log4j: When malicious code is executed on the device allowing attackers to manipulate input data.',
'Update software, Use a Firewall, Use multi-factor authentication.');

INSERT INTO `Router_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(3,
'Routing Table Poisoning: modify packets to be harmful and congest the network to corrupt it.',
'Dynamic ARP Inspection, Statically map ARP tables, encryption.');

INSERT INTO `Router_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(4,
'MITM Attacks: Where victims forward information to attacker thinking its a legit device.',
'Dynamic ARP Inspection, Statically map ARP tables, encryption.');


//switch exploits

INSERT INTO `Switch_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(1,
'MAC Table Attack: The switch is bombarded with fake MAC addressses, causing it to end up full.',
'Port security, Implement AAA, ARP spoofing or IP address spoofing.');

INSERT INTO `Switch_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(2,
'VLAN Attacks: Hopping and double-tagging, allowing attackers to gain access to any VLAN.',
'Port security, disable DTP on all trunk ports.');

INSERT INTO `Switch_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(3,
'DHCP Attacks: Starvation and Spoofing, Like Dos to deny access, rogue DHCP server ip changes.',
'Port security, DHCP snooping.');

INSERT INTO `Switch_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(4,
'ARP Attacks: attackers send ARP replies to other hosts, MITM lead to intercepted data.',
'Packet-filtering firewalls, ARP inspection.');

INSERT INTO `Switch_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(5,
'Address Spoofing: Alter MAC address of host, matching another. forwarded to wrong host.',
'Update software, Use a Firewall, Use VPNs.');

INSERT INTO `Switch_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(6,
'STP Attacks: broadcasts STP bridge protocol units with config info, capturing all trafic.',
'Update software, Use a root guard, use BPDU guard.');


//ap exploits

INSERT INTO `AP_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(1,
'Rogue AP: An access point that has been plugged into network that is not authorised.',
'Wireless intrusion detection, educate the network users, policies.');

INSERT INTO `AP_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(2,
'Evil Twin attack: An access point that looks to be legit and is used to eavesdrop on comms.',
'Educate users, disable auto-connect, use VPN or MFA.');


//asa exploits

INSERT INTO `ASA_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(1,
'TCP/SYN Attack: Attacker sends SYN packets to all ports, responds with SYN-ACKs from each port.',
'Use a filter that disables SYN flood protection, use a password.');

//modem exploits

INSERT INTO `Modem_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(1,
'Denial Of Service: Flooding the device with traffic,causing it to crash the device and make it inaccessible.',
'Implement Firewall, Use a VPN service, Ensure device is up to date.');

INSERT INTO `Modem_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(2,
'Eavesdropping: With the lack of SSL in a modem its easy for the likes of wireshark to sniff traffic.',
'Store in secure location, disable remote access.');

//hub exploits

INSERT INTO `Hub_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(1,
'Half-Duplex Mode: Only 1 device connected can communicate at a time, end up being very slow.',
'Implement a Switch instead.');

INSERT INTO `Hub_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(2,
'Bandwith: It takes up a lot of bandwidth being connected to the network.',
'Implement a Switch instead.');

INSERT INTO `Hub_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(3,
'Eavesdropping: all trafic is shared, so attacker plugs into hub and can intercept all traffic.',
'Implement a Switch instead.');

INSERT INTO `Hub_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(4,
'ARP Attacks: attacker can ARP poison the network, MITM lead to intercepted data.',
'Implement a Switch instead.');

//server exploits

INSERT INTO `Server_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(1,
'Denial Of Service: Flooding the device with traffic,causing it to crash the device and make it inaccessible.',
'Implement Firewall, Use a VPN service, Ensure device is up to date.');

INSERT INTO `Server_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(2,
'Server Spoofing: An attacker impersonates a device that can be used to steal data and access systems.',
'Use encrypted and authenticated protocols, packet filtering.');


INSERT INTO `Server_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(3,
'MITM Attacks: Where victims forward information to attacker thinking its a legit device.',
'Dynamic ARP Inspection, Statically map ARP tables, encryption.');

//bridge exploits

INSERT INTO `Bridge_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(1,
'Unable for complex data: It is unable to handle complex data load such as occurring from WAN.',
'Use a Hub or Repeater instead.');

INSERT INTO `Bridge_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(2,
'No communication between networks: Unable to connect networks together with differnt architectures.',
'Use a Hub or Repeater instead.');

INSERT INTO `Bridge_Exploits` (`id`, `exploit_name`, `fix`) VALUES
(3,
'Cant read Ip address: therefore large networks as WAN which are ip specific cant make use of it.',
'Use a Hub or Repeater instead.');

*/
