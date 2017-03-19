import '../images/Kappa.png'
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';

import 'bootstrap/js/tab.js';
import TabModule from './tabModule';
import './index.css';


var tabModule = new TabModule();

tabModule.renderTab("instagram", 0);
tabModule.renderTab("twitter", 1);
tabModule.renderTab("twitch", 2);

