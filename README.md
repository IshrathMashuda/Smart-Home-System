# Smart Home System

## Overview
The Smart Home System is a simulation application that enables users to manage and control various smart devices such as lights, thermostats, and door locks through a central console. It supports operations like turning devices on or off, scheduling tasks, and automating actions based on specific conditions.

## Features
- **Control Devices**: Turn devices on or off remotely.
- **Scheduling**: Set up schedules to automate device operations at specified times.
- **Automation**: Create triggers based on conditions such as temperature to perform automatic actions.
- **Dynamic Management**: Add or remove devices and manage their states easily.

## Design Patterns Used
- **Observer Pattern**: Implements real-time updates to all devices when a change occurs in the system.
- **Factory Method**: Creates instances of different types of devices using a factory interface.
- **Proxy Pattern**: Provides a proxy to control access to device objects and add additional functionality.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or later
- Command Line Interface (CLI) or Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse

### Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/IshrathMashuda/SmartHomeSystem.git
